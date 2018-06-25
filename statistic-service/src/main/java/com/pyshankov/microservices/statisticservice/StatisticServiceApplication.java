package com.pyshankov.microservices.statisticservice;

import com.pyshankov.microservices.statisticservice.domain.ProductEvent;
import com.pyshankov.microservices.statisticservice.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@EnableEurekaClient
public class StatisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceApplication.class, args);
    }

    @Autowired
    StatisticRepository statisticRepository;

    @RequestMapping(value = "/events")
    public Iterable<ProductEvent> getTest() {
        return statisticRepository.findAll();
    }

//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping(value = "/test")
//    public String getTest() {
//        return "test from Statistic";
//    }


}
