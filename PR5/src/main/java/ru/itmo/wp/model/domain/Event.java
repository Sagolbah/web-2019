package ru.itmo.wp.model.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Event {
    private long id;
    private long userId;
    private Date creationTime;
    private EventType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Event() {
        this.creationTime = new Date();
    }
}
