package com.umfana.domain.models.tag.commands;

import com.umfana.domain.models.tag.TagCommand;
import com.umfana.domain.models.tag.TagId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class DeleteTagCommand extends TagCommand {

    public DeleteTagCommand(
            Instant executedAt,
            TagId id
    ) {
        super(executedAt, id);
    }
}
