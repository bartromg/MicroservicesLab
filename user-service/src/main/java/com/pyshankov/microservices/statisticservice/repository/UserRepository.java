package com.pyshankov.microservices.statisticservice.repository;

import com.pyshankov.microservices.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pyshankov on 4/29/18.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
