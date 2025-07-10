package com.umfana.application.eventstore;

import com.umfana.domain.Event;
import com.umfana.domain.Id;

import java.util.List;

public interface EventStore {

    void saveEvents(Id aggregateId, List<Event> events);

    List<Event> loadEvents(Id aggregateId);
}
