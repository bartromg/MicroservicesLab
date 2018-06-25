package com.pyshankov.microservices.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pyshankov on 4/29/18.
 */
public class Order {
    private String id;
    private LocalDateTime createDate;
    private List<Product> products;
    private BigDecimal price;

}
