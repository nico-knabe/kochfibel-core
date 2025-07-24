package com.umfana.domain.models.tag.events;

import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagDeletedEvent extends TagEvent {

    private final Instant deletedAt;

    public TagDeletedEvent(TagId tagId, Instant deletedAt) {
        super(tagId);
        this.deletedAt = deletedAt;
    }

}
