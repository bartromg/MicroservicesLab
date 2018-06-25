package com.pyshankov.microservices.hazelcast.cache;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastClientConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return HazelcastClient.newHazelcastClient();
    }

    @Bean
    public HazelcastClientTemplate hazelcastClientTemplate() {
        return new HazelcastClientTemplate(hazelcastInstance());
    }

}
