package com.pyshankov.microservices.statisticservice.controller;


import com.pyshankov.microservices.statisticservice.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
@RestController
public class StatisticController {
    @Autowired
    StatisticService statisticService;
//
//    @RequestMapping("/allevents")
//    public List<ProductEvent> events() {
//        List<ProductEvent> events = statisticService
//        if (events.isEmpty()) {
//            return null;
//        }
//        return events;
//    }

//    @RequestMapping("/newevent")
//    public void newEvent(Event event) {
//        statisticService.handleEvent(event);
//    }


//    @RequestMapping("/delete")
//    public void deletAllEvents() {
//        statisticService.clearData();
//    }



}

