package com.pyshankov.microservices.statisticservice.service;

import com.pyshankov.microservices.domain.Event;
import org.springframework.stereotype.Service;

/**
 * Created by pyshankov on 4/30/18.
 */
@Service
public class StatisticService {

//    @RabbitListener(queues = "${amqp.rabbitmq.queue}")
    public void handleEvent(Event event) {
        AmqpConsumer.recievedMessage(event, (message) -> {

            // TODO: store there event variable to cassandra db, @valera
        });
    }



}
