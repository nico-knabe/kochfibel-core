package com.umfana.domain;

import java.time.Instant;

public abstract class Command {

    private final Instant executedAt;

    protected Command(Instant executedAt) {
        this.executedAt = executedAt;
    }

    public Instant getExecutedAt() {
        return executedAt;
    }
}
