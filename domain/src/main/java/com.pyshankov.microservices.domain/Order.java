package com.pyshankov.microservices.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pyshankov on 4/29/18.
 */

@NoArgsConstructor
@Data public class Order {
    private String id;
    private LocalDateTime createDate;
    private List<Product> products;
    private BigDecimal price;
    private User user;

}
