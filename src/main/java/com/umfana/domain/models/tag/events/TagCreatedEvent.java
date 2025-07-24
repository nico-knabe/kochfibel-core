package com.umfana.domain.models.tag.events;

import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagId;
import com.umfana.domain.models.tag.valueobjects.TagColor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

}
