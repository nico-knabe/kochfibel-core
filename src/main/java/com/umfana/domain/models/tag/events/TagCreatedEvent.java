package com.umfana.domain.models.tag.events;

import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;

import java.time.Instant;

public class TagCreatedEvent extends TagEvent {

    private final Instant createdAt;
    private final String name;
    private final TagColor color;

    public TagCreatedEvent(TagId tagId, Instant createdAt, String name, TagColor color) {
        super(tagId);
        this.createdAt = createdAt;
        this.name = name;
        this.color = color;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public TagColor getColor() {
        return color;
    }
}
