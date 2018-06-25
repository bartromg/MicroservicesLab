package com.pyshankov.microservices.domain;

/**
 * Created by pyshankov on 4/29/18.
 */
public class ProductEvent extends Event {
    private Long productId;
    private ProductEventType productEventType;

    public ProductEvent(Long productId, ProductEventType productEventType) {
        this.productId = productId;
        this.productEventType = productEventType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductEventType getProductEventType() {
        return productEventType;
    }

    public void setProductEventType(ProductEventType productEventType) {
        this.productEventType = productEventType;
    }
}
