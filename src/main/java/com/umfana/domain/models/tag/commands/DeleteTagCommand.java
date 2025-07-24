package com.umfana.domain.models.tag.commands;

import com.umfana.domain.models.tag.TagCommand;
import com.umfana.domain.models.tag.TagId;

import java.time.Instant;

public final class DeleteTagCommand extends TagCommand {
    private final TagId id;

    public DeleteTagCommand(
            Instant executedAt,
            TagId id
    ) {
        super(executedAt);
        this.id = id;
    }

    public TagId id() {
        return id;
    }
}
