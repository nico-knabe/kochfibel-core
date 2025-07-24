package com.umfana.application.eventstore;

import com.umfana.domain.Event;
import com.umfana.domain.Id;

import java.util.List;

public interface EventStore<I extends Id, E extends Event> {

    void saveEvents(I aggregateId, List<E> events);

    List<E> loadEvents(I aggregateId);
}
