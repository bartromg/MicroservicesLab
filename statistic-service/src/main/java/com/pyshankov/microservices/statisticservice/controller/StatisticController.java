package com.pyshankov.microservices.statisticservice.controller;

import com.pyshankov.microservices.domain.Event;
import com.pyshankov.microservices.statisticservice.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
@RestController
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    @RequestMapping("/allevents")
    public List<Event> eventByName(@RequestParam(value="name") String name) {
        List<Event> events = statisticService.getEventsByName(name);
        if(events.isEmpty()){
            return null;
        }
        return events;
    }

    @RequestMapping("/newevent")
    public void newEvent(Event event) {
        statisticService.handleEvent(event);
    }


    @RequestMapping("/delete")
    public void deletAllEvents() {
        statisticService.clearData();
    }



}

