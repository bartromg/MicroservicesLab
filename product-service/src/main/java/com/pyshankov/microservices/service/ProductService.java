package com.pyshankov.microservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.domain.ProductEvent;
import com.pyshankov.microservices.domain.ProductEventType;
import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import com.pyshankov.microservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
  
    @Autowired
    private AmqpProducerService amqpProducerService;

    @Autowired
    private HazelcastClientTemplate hazelcastClientTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Product persist(Product product, String token) {
        User user = hazelcastClientTemplate.getUserFromCacheByToken(token);
        product.setOwner(user.getEmail());
        Product product1 = productRepository.save(product);
        amqpProducerService.produceMsg(getAsJson(new ProductEvent(product.getId(), null, ProductEventType.CREATE_PRODUCT)));
        return product1;
    }

    public void deleteProduct(String id) {
        productRepository.delete(id);
        amqpProducerService.produceMsg(getAsJson(new ProductEvent(id, null, ProductEventType.DELETE_PRODUCT)));
    }

    public Product getProduct(String id) {
        amqpProducerService.produceMsg(getAsJson(new ProductEvent(id, null, ProductEventType.VIEW_PRODUCT)));
        return productRepository.findOne(id);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findByBoughtFalse();
    }

    public List<Product> getAllUserProducts(String email) {
        return productRepository.findByOwnerEmail(email);
    }

    public void buyProduct(Product product) {
        product.setBought(true);
        productRepository.save(product);
    }
    private String getAsJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

}
