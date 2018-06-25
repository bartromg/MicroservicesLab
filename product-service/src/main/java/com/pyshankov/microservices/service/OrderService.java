package com.pyshankov.microservices.service;

import com.pyshankov.microservices.domain.Order;
import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import com.pyshankov.microservices.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;

    @Autowired
    private HazelcastClientTemplate hazelcastClientTemplate;

    public List<Order> getUserOrders(String userEmail) {
        return orderRepository.findByUser(userEmail);
    }

    public Order persist(Order order, String token) {
        User user = hazelcastClientTemplate.getUserFromCacheByToken(token);
        order.setUser(user.getEmail());
        order.setCreateDate(LocalDateTime.now());
        Product product = order.getProducts().get(0);
        order.setPrice(product.getPrice());
        productService.buyProduct(product);
        return orderRepository.save(order);
    }

    public void delete(String id) {
        orderRepository.delete(id);
    }

    public Order getOrder(String id) {
        return orderRepository.findOne(id);
    }

    public List<Order> getOrders(User user) {
        return orderRepository.findByUser(user.getEmail());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
