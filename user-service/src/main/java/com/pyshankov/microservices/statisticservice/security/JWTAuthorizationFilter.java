package com.pyshankov.microservices.statisticservice.security;

import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.HEADER_STRING;
import static com.pyshankov.microservices.statisticservice.security.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomUserDetailsService customUserDetailsService;

    private HazelcastClientTemplate hazelcastClientTemplate;

    public JWTAuthorizationFilter(AuthenticationManager authManager, CustomUserDetailsService customUserDetailsService, HazelcastClientTemplate hazelcastClientTemplate) {
        super(authManager);
        this.customUserDetailsService = customUserDetailsService;
        this.hazelcastClientTemplate = hazelcastClientTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null && hazelcastClientTemplate.getContainsUserInCacheByToken(token)) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(user);
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}