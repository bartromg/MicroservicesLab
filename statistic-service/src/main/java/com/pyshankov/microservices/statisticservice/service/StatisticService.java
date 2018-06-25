package com.pyshankov.microservices.statisticservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyshankov.microservices.statisticservice.config.RabbitConfig;
import com.pyshankov.microservices.statisticservice.domain.ProductEvent;
import com.pyshankov.microservices.statisticservice.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by pyshankov on 4/30/18.
 */
@Component
public class StatisticService {

    static final Logger logger = LoggerFactory.getLogger(StatisticService.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    StatisticRepository statisticRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void handleEvent(String event) {
        AmqpConsumer.recievedMessage(getProductEventFromJson(event), (message) -> {
            logger.info("Order Received: " + message);
            ProductEvent newProductEvent = getProductEventFromJson(event);
            statisticRepository.save(newProductEvent);
        });
    }

    ProductEvent getProductEventFromJson(String productEventJson) {
        try {
            return objectMapper.readValue(productEventJson, ProductEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

