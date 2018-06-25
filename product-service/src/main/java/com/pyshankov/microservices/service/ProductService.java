package com.pyshankov.microservices.service;

import com.pyshankov.microservices.domain.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private AmqpProducerService amqpProducerService;

    public void createProduct() {
        //TODO: save product to db, send message to rabbitmq, @alex
        amqpProducerService.produceMsg(new ProductEvent());
    }

    public void deleteProduct() {

    }

    public void getProductDetails(String productId) {

    }

    public void getAllProducts() {

    }

}
