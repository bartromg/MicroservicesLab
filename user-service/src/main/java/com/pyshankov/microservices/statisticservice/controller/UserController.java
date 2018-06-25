package com.pyshankov.microservices.statisticservice.controller;

import com.pyshankov.microservices.statisticservice.service.AmqpProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AmqpProducerService amqpProducerService;

    @RequestMapping(value = "/message/{value}")
    public String getMessage(@PathVariable String value) {
        amqpProducerService.produceMsg(value);
        return value;
    }

}
