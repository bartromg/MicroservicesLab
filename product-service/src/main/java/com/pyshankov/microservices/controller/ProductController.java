package com.pyshankov.microservices.controller;

import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public static final String HEADER_STRING = "Authorization";

    @GetMapping
    public List<Product> getActiveProducts() {
        return productService.getAllActiveProducts();
    }

    @GetMapping(value = "/user/{email}")
    public List<Product> getUserProducts(@PathVariable String email) {
        return productService.getAllUserProducts(email);
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product createProduct(@RequestBody Product product, @RequestHeader HttpHeaders headers) {
        // TODO check if product is valid
        return productService.persist(product, headers.getFirst(HEADER_STRING));
    }

}
