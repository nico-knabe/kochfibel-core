package com.umfana.domain.models.tag;

import com.umfana.domain.Command;

import java.time.Instant;

public class TagCommand extends Command {
    protected TagCommand(Instant executedAt) {
        super(executedAt);
    }
}
