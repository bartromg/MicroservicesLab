package com.pyshankov.microservices.domain;

public class ProductEvent {

    private static final long serialVersionUID = -4223434L;

    private String productId;
    private ProductEventType productEventType;
    //    private LocalDateTime timestamp;
    private String userId;

    public ProductEvent() {
    }

    public ProductEvent(String productId, String userId, ProductEventType productEventType) {
        this.productId = productId;
        this.productEventType = productEventType;
        this.userId = userId;
//        timestamp = LocalDateTime.now();
    }

    public String getProductId() {
        return productId;
    }

    public ProductEventType getProductEventType() {
        return productEventType;
    }

//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }

    public String getUserId() {
        return userId;
    }
}
