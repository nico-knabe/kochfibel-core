package com.umfana.domain.models.tag.commands;

import com.umfana.domain.Command;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;

public record CreateTagCommand(
        TagId id,
        String name,
        TagColor color
) implements Command {
}
