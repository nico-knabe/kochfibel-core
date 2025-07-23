package com.umfana.domain.models.tag;

import com.umfana.domain.DomainException;
import com.umfana.domain.Event;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;
import com.umfana.domain.models.tag.events.*;
import com.umfana.domain.models.tag.valueobjects.TagColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tag {

    private enum TagState {
        INIT, CREATED, DELETED
    }

    private TagId id;
    private TagState state;
    private String name;
    private TagColor color;

    private final List<Event> uncommittedEvents = new ArrayList<>();

    private Tag(TagState state) {
        // private to enforce creation via Event replay
        this.state = state;
    }

    public static Tag rehydrate(List<Event> events) {
        Tag tag = new Tag(TagState.INIT);
        events.forEach(tag::apply);
        return tag;
    }

    public void create(ExecutedCommand<CreateTagCommand> command) {
        if (state.equals(TagState.CREATED) || state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.CouldNotCreateTag);
        }
        if (command.command().name() == null || command.command().name().isEmpty()) {
            throw new DomainException(DomainException.Key.TagNameDoesNotBeBlank);
        }
        TagEvent createdEvent = new TagCreatedEvent(
                command.command().id(),
                command.executedAt(),
                command.command().name(),
                command.command().color()
        );
        recordEvents(List.of(createdEvent));
    }

    public void change(ExecutedCommand<ChangeTagCommand> command) {
        if (command.command().id() == null || !command.command().id().getValue().equals(this.id.getValue())) {
            throw new DomainException(DomainException.Key.WrongTagId);
        }
        if (state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.TagIsDeleted);
        }
        if (command.command().name() == null || command.command().name().isEmpty()) {
            throw new DomainException(DomainException.Key.TagNameDoesNotBeBlank);
        }
        TagEvent colorChangedEvent = new TagColorChangedEvent(
                this.id,
                command.executedAt(),
                command.command().color()
        );
        TagEvent nameChangedEvent = new TagNameChangedEvent(
                this.id,
                command.executedAt(),
                command.command().name()
        );
        recordEvents(List.of(colorChangedEvent, nameChangedEvent));
    }

    public void delete(ExecutedCommand<DeleteTagCommand> command) {
        if (command.command().id() == null || !command.command().id().getValue().equals(this.id.getValue())) {
            throw new DomainException(DomainException.Key.WrongTagId);
        }
        if (state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.CouldNotDeleteTag);
        }
        TagEvent deletedEvent = new TagDeletedEvent(command.command().id(), command.executedAt());
        recordEvents(List.of(deletedEvent));
    }

    private void recordEvents(List<Event> events) {
        this.uncommittedEvents.addAll(events);
        events.forEach(this::apply);
    }

    private void apply(Event event) {
        switch (event) {
            case TagCreatedEvent e -> {
                this.id = e.getTagId();
                this.state = TagState.CREATED;
                this.color = e.getColor();
                this.name = e.getName();
            }
            case TagColorChangedEvent e -> this.color = e.getColor();
            case TagNameChangedEvent e -> this.name = e.getName();
            case TagDeletedEvent e -> {
                this.state = TagState.DELETED;
            }
            default -> throw new UnsupportedOperationException("Unknown event type: " + event.getClass());
        }
    }

    public List<Event> getUncommittedEvents() {
        return Collections.unmodifiableList(uncommittedEvents);
    }

    public void markEventsCommitted() {
        ensureNotDeleted();
        uncommittedEvents.clear();
    }

    public TagId getId() {
        ensureNotDeleted();
        return id;
    }

    public String getName() {
        ensureNotDeleted();
        return name;
    }

    public TagColor getColor() {
        ensureNotDeleted();
        return color;
    }

    public boolean isDeleted() {
        return state.equals(TagState.DELETED);
    }

    private void ensureNotDeleted() {
        if (isDeleted()) {
            throw new DomainException(DomainException.Key.TagIsDeleted);
        }
    }

}
