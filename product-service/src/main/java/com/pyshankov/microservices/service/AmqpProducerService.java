package com.pyshankov.microservices.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AmqpProducerService {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${amqp.rabbitmq.exchange}")
    private String exchange;

    @Value("${amqp.rabbitmq.routingkey}")
    private String routingKey;

    public <T> void produceMsg(T msg) {
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
    }

}
