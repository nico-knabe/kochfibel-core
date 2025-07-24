package com.umfana.domain.models.tag;

import com.umfana.domain.DomainException;
import com.umfana.domain.models.Aggregate;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;
import com.umfana.domain.models.tag.events.TagColorChangedEvent;
import com.umfana.domain.models.tag.events.TagCreatedEvent;
import com.umfana.domain.models.tag.events.TagDeletedEvent;
import com.umfana.domain.models.tag.events.TagNameChangedEvent;
import com.umfana.domain.models.tag.valueobjects.TagColor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@Getter
public class Tag extends Aggregate<TagCommand, TagEvent> {

    private enum TagState {
        INIT, CREATED, DELETED
    }

    private TagId id;
    private TagState state = TagState.INIT;
    private String name;
    private TagColor color;

    public Tag(List<TagEvent> events) {
        super(events);
    }


    @Override
    protected void apply(TagEvent event) {
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

    @Override
    public void handle(TagCommand command) {
        switch (command) {
            case CreateTagCommand c -> create(c);
            case ChangeTagCommand c -> change(c);
            case DeleteTagCommand c -> delete(c);
            default -> throw new UnsupportedOperationException("Unknown event type: " + command.getClass());
        }

    }

    private void create(CreateTagCommand command) {
        if (state.equals(TagState.CREATED) || state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.CouldNotCreateTag);
        }
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new DomainException(DomainException.Key.TagNameDoesNotBeBlank);
        }
        if (command.getColor() == null) {
            throw new DomainException(DomainException.Key.TagColorDoesNotBeNull);
        }

        var createdEvent = new TagCreatedEvent(
                command.getId(),
                command.getExecutedAt(),
                command.getName(),
                command.getColor()
        );
        recordEvents(List.of(createdEvent));
    }

    private void change(ChangeTagCommand command) {
        if (command.getId() == null || !command.getId().getValue().equals(this.getId().getValue())) {
            throw new DomainException(DomainException.Key.WrongTagId);
        }
        if (state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.TagIsDeleted);
        }
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new DomainException(DomainException.Key.TagNameDoesNotBeBlank);
        }
        var colorChangedEvent = new TagColorChangedEvent(
                this.id,
                command.getExecutedAt(),
                command.getColor()
        );
        var nameChangedEvent = new TagNameChangedEvent(
                this.id,
                command.getExecutedAt(),
                command.getName()
        );
        recordEvents(List.of(colorChangedEvent, nameChangedEvent));
    }

    private void delete(DeleteTagCommand command) {
        if (command.getId() == null || !command.getId().getValue().equals(this.id.getValue())) {
            throw new DomainException(DomainException.Key.WrongTagId);
        }
        if (state.equals(TagState.DELETED)) {
            throw new DomainException(DomainException.Key.CouldNotDeleteTag);
        }
        var deletedEvent = new TagDeletedEvent(command.getId(), command.getExecutedAt());
        recordEvents(List.of(deletedEvent));
    }

    public boolean isDeleted() {
        return state.equals(TagState.DELETED);
    }

}
