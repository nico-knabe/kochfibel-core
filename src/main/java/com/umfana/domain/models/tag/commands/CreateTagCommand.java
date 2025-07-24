package com.umfana.domain.models.tag.commands;

import com.umfana.domain.models.tag.TagCommand;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class CreateTagCommand extends TagCommand {
    private final String name;
    private final TagColor color;

    public CreateTagCommand(
            Instant executedAt,
            TagId id,
            String name,
            TagColor color
    ) {
        super(executedAt, id);
        this.name = name;
        this.color = color;
    }
}
