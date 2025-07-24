package com.umfana.domain.models;

import com.umfana.domain.Command;
import com.umfana.domain.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Aggregate {

    protected Aggregate(List<Event> events) {
        events.forEach(this::apply);
    }

    private final List<Event> uncommittedEvents = new ArrayList<>();

    public void markEventsAsCommitted() {
        uncommittedEvents.clear();
    }

    public void addEvent(Event event) {
        uncommittedEvents.add(event);
    }

    public List<Event> getUncommittedEvents() {
        return Collections.unmodifiableList(uncommittedEvents);
    }

    public void markEventsCommitted() {
        uncommittedEvents.clear();
    }

    protected void recordEvents(List<Event> events) {
        events.forEach(this::addEvent);
        events.forEach(this::apply);
    }

    protected abstract void apply(Event event);

    protected abstract void handle(Command command);
}
