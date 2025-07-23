package com.umfana.domain.models.tag;

import com.umfana.domain.DomainException;
import com.umfana.domain.Event;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.Aggregate;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;
import com.umfana.domain.models.tag.events.TagColorChangedEvent;
import com.umfana.domain.models.tag.events.TagCreatedEvent;
import com.umfana.domain.models.tag.events.TagDeletedEvent;
import com.umfana.domain.models.tag.events.TagNameChangedEvent;
import com.umfana.domain.models.tag.valueobjects.TagColor;

import java.util.List;

public class Tag extends Aggregate {

    private enum TagState {
        INIT, CREATED, DELETED
    }

    private TagId id;
    private TagState state = TagState.INIT;
    private String name;
    private TagColor color;

    public Tag(List<Event> events) {
        super(events);
    }


    public void create(ExecutedCommand<CreateTagCommand> command) {
        if (state.equals(TagState.CREATED) || state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.CouldNotCreateTag);
        }
        if (command.command().name() == null || command.command().name().isEmpty()) {
            throw new DomainException(DomainException.Key.TagNameDoesNotBeBlank);
        }
        if (command.command().color() == null) {
            throw new DomainException(DomainException.Key.TagColorDoesNotBeNull);
        }

        Event createdEvent = new TagCreatedEvent(
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
        var colorChangedEvent = new TagColorChangedEvent(
                this.id,
                command.executedAt(),
                command.command().color()
        );
        var nameChangedEvent = new TagNameChangedEvent(
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
        Event deletedEvent = new TagDeletedEvent(command.command().id(), command.executedAt());
        recordEvents(List.of(deletedEvent));
    }

    @Override
    protected void apply(Event event) {
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
