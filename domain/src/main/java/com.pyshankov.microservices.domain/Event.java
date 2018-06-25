package com.pyshankov.microservices.domain;

import java.time.LocalDateTime;

/**
 * Created by pyshankov on 4/29/18.
 */
public abstract class Event {
    protected String eventId;
    protected String userId;
    protected LocalDateTime timestamp;
}
