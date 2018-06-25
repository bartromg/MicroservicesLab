package com.pyshankov.microservices.statisticservice.service;

import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private HazelcastClientTemplate hazelcastClientTemplate;

    public void createUser() {

    }

}
