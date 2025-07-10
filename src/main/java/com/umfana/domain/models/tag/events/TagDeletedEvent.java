package com.umfana.domain.models.tag.events;

import com.umfana.domain.models.tag.TagId;

import java.time.Instant;

public class TagDeletedEvent extends TagEvent {

    private final Instant deletedAt;

    public TagDeletedEvent(TagId tagId, Instant deletedAt) {
        super(tagId);
        this.deletedAt = deletedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
