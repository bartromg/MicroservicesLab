package com.pyshankov.microservices.controller;

import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/products/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/products")
    public Product createProduct(@RequestBody Product product, @RequestHeader HttpHeaders headers) {
        // TODO check if product is valid
        return productService.persist(product, headers.getFirst(HEADER_STRING));
    }

}
