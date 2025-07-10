package com.umfana.domain;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {

    private final UUID id;
    private final Instant occurredAt;

    public Event() {
        this.id = UUID.randomUUID();
        this.occurredAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
