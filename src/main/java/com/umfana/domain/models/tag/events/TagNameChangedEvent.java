package com.umfana.domain.models.tag.events;

import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagId;

import java.time.Instant;

public class TagNameChangedEvent extends TagEvent {

    private final Instant changedAt;
    private final String name;

    public TagNameChangedEvent(TagId tagId, Instant changedAt, String name) {
        super(tagId);
        this.changedAt = changedAt;
        this.name = name;
    }

    public Instant getChangedAt() {
        return changedAt;
    }

    public String getName() {
        return name;
    }
}
