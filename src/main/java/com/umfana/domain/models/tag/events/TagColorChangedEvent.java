package com.umfana.domain.models.tag.events;

import com.umfana.domain.Event;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;

import java.time.Instant;

public class TagColorChangedEvent extends Event {

    private final TagId tagId;
    private final Instant changedAt;
    private final TagColor color;

    public TagColorChangedEvent(TagId tagId, Instant changedAt, TagColor color) {
        this.tagId = tagId;
        this.changedAt = changedAt;
        this.color = color;
    }

    public TagId getTagId() {
        return tagId;
    }

    public Instant getChangedAt() {
        return changedAt;
    }

    public TagColor getColor() {
        return color;
    }
}
