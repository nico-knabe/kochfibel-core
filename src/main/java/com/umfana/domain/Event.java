package com.umfana.domain;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class Event {

    private final UUID id;
    private final Instant occurredAt;

    public Event() {
        this.id = UUID.randomUUID();
        this.occurredAt = Instant.now();
    }

}
