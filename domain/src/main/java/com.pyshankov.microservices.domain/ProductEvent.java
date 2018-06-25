package com.pyshankov.microservices.domain;

/**
 * Created by pyshankov on 4/29/18.
 */
public class ProductEvent extends Event {
    private String productId;
    private ProductEventType productEventType;
}
