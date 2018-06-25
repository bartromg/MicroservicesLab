package com.pyshankov.microservices.statisticservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import com.pyshankov.microservices.statisticservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.EXPIRATION_TIME;
import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.HEADER_STRING;
import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.SECRET;
import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;
    private HazelcastClientTemplate hazelcastClientTemplate;
    private UserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, HazelcastClientTemplate hazelcastClientTemplate, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.hazelcastClientTemplate = hazelcastClientTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = objectMapper
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        CustomUserDetailsService.CustomUserDetails user = (CustomUserDetailsService.CustomUserDetails) auth.getPrincipal();


        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        hazelcastClientTemplate.putUserToCacheByToken(TOKEN_PREFIX + token, userRepository.findByEmail(user.getUsername()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.addHeader("Content-Type", "application/json");
        res.getWriter().print(objectMapper.writeValueAsString(new User(user.getUsername(), user.getPassword())));
    }
}