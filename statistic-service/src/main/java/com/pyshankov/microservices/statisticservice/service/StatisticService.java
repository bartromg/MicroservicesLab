package com.pyshankov.microservices.statisticservice.service;

import com.pyshankov.microservices.domain.Event;
import com.pyshankov.microservices.statisticservice.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pyshankov on 4/30/18.
 */
@Service
public class StatisticService {

//    public void handleEvent(Event event) {
//        AmqpConsumer.recievedMessage(event, (message) -> {
//
//            // TODO: store there event variable to cassandra db, @valera
//        });
//    }

    @Autowired
    StatisticRepository statisticRepository;

    public StatisticService() {
    }

    public void handleEvent(Event event) {
//        Event event = new Event(UUID.randomUUID(), "77777", LocalDateTime.now());
        statisticRepository.save(event);
    }

    public List<Event> getEventsByName(String firstname) {
        return statisticRepository.findByFirstname(firstname);
    }

    public void clearData() {
        statisticRepository.deleteAll();
    }
}
