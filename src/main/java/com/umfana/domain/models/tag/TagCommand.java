package com.umfana.domain.models.tag;

import com.umfana.domain.Command;

import java.time.Instant;

public class TagCommand extends Command {

    private final TagId id;

    protected TagCommand(Instant executedAt, TagId id) {
        super(executedAt);
        this.id = id;
    }

    public TagId id() {
        return id;
    }
}
