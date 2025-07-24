package com.umfana.domain;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class Command {

    private final Instant executedAt;

    protected Command(Instant executedAt) {
        this.executedAt = executedAt;
    }

}
