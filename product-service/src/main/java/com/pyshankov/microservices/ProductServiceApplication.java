package com.pyshankov.microservices;


import com.pyshankov.microservices.hazelcast.cache.HazelcastClientConfig;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Import(HazelcastClientConfig.class)
@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProductServiceApplication {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String getTest() {
        return "test from Product";
    }

    @Component
    @Order(1)
    public static class SecurityTokenFilter implements Filter {

        @Autowired
        private HazelcastClientTemplate hazelcastClientTemplate;

        public static final String SECRET = "SecretKeyToGenJWTs";
        public static final long EXPIRATION_TIME = 864_000_000; // 10 days
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final String SIGN_UP_URL = "/users/sign-up";

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
            String header = ((HttpServletRequest) request).getHeader(HEADER_STRING);
            if (hazelcastClientTemplate.getContainsUserInCacheByToken(header)) {
                chain.doFilter(request, response);
            } else throw new RuntimeException();
        }

        @Override
        public void destroy() {

        }


    }

}
