package com.umfana.domain.models.tag;

import com.umfana.domain.Event;
import lombok.Getter;

@Getter
public abstract class TagEvent extends Event {

    private final TagId tagId;

    public TagEvent(TagId tagId) {
        this.tagId = tagId;
    }
}
