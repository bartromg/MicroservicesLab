package com.pyshankov.microservices.statisticservice.domain;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("ProductEvent")
public class ProductEvent {

    private static final long serialVersionUID = -4223434L;


    @PrimaryKey
    private UUID id;
    private String productId;
    private ProductEventType productEventType;
    private LocalDateTime timestamp;
    private String userId;

    public ProductEvent() {
    }

    public ProductEvent(UUID id, String productId, String userId, ProductEventType productEventType) {
        this.id = id;
        this.productId = productId;
        this.productEventType = productEventType;
        this.userId = userId;
        timestamp = LocalDateTime.now();
    }

    public String getProductId() {
        return productId;
    }

    public ProductEventType getProductEventType() {
        return productEventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }
}