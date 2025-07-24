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
public class TagColorChangedEvent extends TagEvent {

    private final Instant changedAt;
    private final TagColor color;

    public TagColorChangedEvent(TagId tagId, Instant changedAt, TagColor color) {
        super(tagId);
        this.changedAt = changedAt;
        this.color = color;
    }

}
