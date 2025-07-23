package com.umfana.domain.models.tag.events;

import com.umfana.domain.Event;
import com.umfana.domain.models.tag.TagId;

import java.time.Instant;

public class TagDeletedEvent extends Event {

    private final TagId tagId;
    private final Instant deletedAt;

    public TagDeletedEvent(TagId tagId, Instant deletedAt) {
        this.tagId = tagId;
        this.deletedAt = deletedAt;
    }

    public TagId getTagId() {
        return tagId;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
