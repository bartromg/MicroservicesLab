package com.pyshankov.microservices.controller;

import com.pyshankov.microservices.domain.Order;
import com.pyshankov.microservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public static final String HEADER_STRING = "Authorization";

    @GetMapping(value = "/user/{email}")
    public List<Order> getUserOrders(@PathVariable String email) {
        return orderService.getUserOrders(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Order createOrder(@RequestBody Order order, @RequestHeader HttpHeaders headers) {
        return orderService.persist(order, headers.getFirst(HEADER_STRING));
    }

}
