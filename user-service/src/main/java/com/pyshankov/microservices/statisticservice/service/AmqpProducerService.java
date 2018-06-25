package com.pyshankov.microservices.statisticservice.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpProducerService {

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    @Autowired
    private AmqpTemplate amqpTemplate;


    public <T> void produceMsg(T msg) {
        amqpTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", msg);
    }

}
