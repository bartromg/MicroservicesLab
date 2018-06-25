package com.pyshankov.microservices.statisticservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class AmqpConsumer {

    @RabbitListener(queues = "${amqp.rabbitmq.queue}")
    public <T> void recievedMessage(T msg, Consumer<T> messageConsumer) {
        messageConsumer.accept(msg);
    }

}
