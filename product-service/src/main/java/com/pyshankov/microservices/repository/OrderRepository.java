package com.pyshankov.microservices.repository;

import com.pyshankov.microservices.domain.Order;
import com.pyshankov.microservices.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexey on 30/4/17.
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUser(User user);
}
