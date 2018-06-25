package com.pyshankov.microservices.repository;

import com.pyshankov.microservices.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexey on 30/4/17.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);

    List<Product> findByBoughtFalse();

    List<Product> findByOwnerEmail(String ownerEmail);
}
