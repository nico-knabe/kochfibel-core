package com.umfana.domain.models;

import com.umfana.domain.Command;
import com.umfana.domain.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Aggregate<C extends Command, E extends Event> {

    protected Aggregate(List<E> events) {
        events.forEach(this::apply);
    }

    private final List<E> uncommittedEvents = new ArrayList<>();

    public void markEventsAsCommitted() {
        uncommittedEvents.clear();
    }

    public void addEvent(E event) {
        uncommittedEvents.add(event);
    }

    public List<E> getUncommittedEvents() {
        return Collections.unmodifiableList(uncommittedEvents);
    }

    public void markEventsCommitted() {
        uncommittedEvents.clear();
    }

    protected void recordEvents(List<E> events) {
        events.forEach(this::addEvent);
        events.forEach(this::apply);
    }

    protected abstract void apply(E event);

    public abstract void handle(C command);
}
