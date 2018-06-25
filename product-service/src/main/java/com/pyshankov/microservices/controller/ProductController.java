package com.pyshankov.microservices.controller;

import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(value = "/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping(value = "/product/{id}")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        // TODO check if product is valid
        productService.persist(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
