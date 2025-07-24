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
public class TagNameChangedEvent extends TagEvent {

    private final Instant changedAt;
    private final String name;

    public TagNameChangedEvent(TagId tagId, Instant changedAt, String name) {
        super(tagId);
        this.changedAt = changedAt;
        this.name = name;
    }

}
