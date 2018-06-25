package com.pyshankov.microservices.service;

import com.pyshankov.microservices.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpProducerService {


    @Autowired
    private AmqpTemplate amqpTemplate;


    public <T> void produceMsg(T msg) {
        amqpTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, msg);
    }

}
