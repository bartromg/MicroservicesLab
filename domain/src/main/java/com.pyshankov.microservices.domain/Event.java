package com.pyshankov.microservices.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
@Table("event")
public class Event {

    @PrimaryKey
    private UUID id;
    private String userId;
    private LocalDateTime timestamp;

    public Event(){
    }

    public Event(UUID id, String userId, LocalDateTime age) {
        this.id = id;
        this.userId = userId;
        this.timestamp = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getAge() {
        return timestamp;
    }

    public void setAge(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

