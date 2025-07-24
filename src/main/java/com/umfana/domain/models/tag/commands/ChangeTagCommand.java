package com.umfana.domain.models.tag.commands;

import com.umfana.domain.models.tag.TagCommand;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;

import java.time.Instant;

public final class ChangeTagCommand extends TagCommand {

    private final String name;
    private final TagColor color;

    public ChangeTagCommand(
            Instant executedAt,
            TagId id,
            String name,
            TagColor color
    ) {
        super(executedAt, id);
        this.name = name;
        this.color = color;
    }

    public String name() {
        return name;
    }

    public TagColor color() {
        return color;
    }
}
