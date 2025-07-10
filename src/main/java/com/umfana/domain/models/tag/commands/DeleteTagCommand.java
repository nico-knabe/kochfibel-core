package com.umfana.domain.models.tag.commands;

import com.umfana.domain.Command;
import com.umfana.domain.models.tag.TagId;

public record DeleteTagCommand(
        TagId id
) implements Command {
}
