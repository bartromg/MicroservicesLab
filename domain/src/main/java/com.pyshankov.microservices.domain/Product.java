package com.pyshankov.microservices.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by pyshankov on 4/29/18.
 */
@NoArgsConstructor
@Data public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
