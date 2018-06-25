package com.pyshankov.microservices.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by pyshankov on 4/29/18.
 */
public abstract class Event implements Serializable {

    private static final long serialVersionUID = -4243434L;


    protected String eventId;
    protected String userId;
    protected LocalDateTime timestamp;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
