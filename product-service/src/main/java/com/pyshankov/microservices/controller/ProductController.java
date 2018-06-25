package com.pyshankov.microservices.controller;

import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import com.pyshankov.microservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public static final String HEADER_STRING = "Authorization";

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(value = "/product/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PostMapping(value = "/product/{id}")
    public ResponseEntity<String> createProduct(@RequestBody Product product, @RequestHeader HttpHeaders headers) {
        // TODO check if product is valid
        productService.persist(product, headers.getFirst(HEADER_STRING));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
