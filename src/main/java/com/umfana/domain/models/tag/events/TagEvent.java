package com.umfana.domain.models.tag.events;

import com.umfana.domain.Event;
import com.umfana.domain.models.tag.TagId;

public class TagEvent extends Event {

    private final TagId tagId;

    public TagEvent(TagId tagId) {
        this.tagId = tagId;
    }

    public TagId getTagId() {
        return tagId;
    }
}
