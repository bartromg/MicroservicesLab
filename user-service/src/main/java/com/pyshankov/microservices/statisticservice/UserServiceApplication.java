package com.pyshankov.microservices.statisticservice;

import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientConfig;
import com.pyshankov.microservices.statisticservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@Import(HazelcastClientConfig.class)
@RestController
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            userRepository.deleteAll();
            userRepository.save(new User("test", passwordEncoder.encode("123321")));
            userRepository.save(new User("user2", passwordEncoder.encode("1234")));
        };
    }


}
