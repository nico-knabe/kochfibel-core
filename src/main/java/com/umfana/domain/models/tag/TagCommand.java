package com.umfana.domain.models.tag;

import com.umfana.domain.Command;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class TagCommand extends Command {

    private final TagId id;

    protected TagCommand(Instant executedAt, TagId id) {
        super(executedAt);
        this.id = id;
    }
}
