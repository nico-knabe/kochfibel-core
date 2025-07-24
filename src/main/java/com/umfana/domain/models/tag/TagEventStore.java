package com.umfana.domain.models.tag;

import com.umfana.application.eventstore.EventStore;

public interface TagEventStore extends EventStore<TagId, TagEvent> {
}
