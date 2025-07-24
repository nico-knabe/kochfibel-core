package com.umfana.domain.models.tag;

import com.umfana.domain.Event;

public class TagEvent extends Event {

    private final TagId id;

    public TagEvent(TagId id) {
        this.id = id;
    }

    public TagId id() {
        return id;
    }
}
