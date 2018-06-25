package com.pyshankov.microservices.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Document
public class Order {
    @Id
    private String id;
    private LocalDateTime createDate;
    private List<Product> products;
    private BigDecimal price;
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
  
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createDate=" + createDate +
                ", products=" + products +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
