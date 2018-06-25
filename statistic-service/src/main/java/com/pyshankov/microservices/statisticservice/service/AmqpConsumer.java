package com.pyshankov.microservices.statisticservice.service;

import java.util.function.Consumer;


public class AmqpConsumer {

    public static <T> void recievedMessage(T msg, Consumer<T> messageConsumer) {
        messageConsumer.accept(msg);
    }

}
