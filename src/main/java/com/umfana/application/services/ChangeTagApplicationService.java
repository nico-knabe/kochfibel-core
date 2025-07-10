package com.umfana.application.services;

import com.umfana.application.eventstore.EventStore;
import com.umfana.domain.DomainException;
import com.umfana.domain.Event;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.services.IsTagNameUniqueDomainService;

import java.util.List;

public class ChangeTagApplicationService extends ApplicationService<ChangeTagCommand> {
    private final IsTagNameUniqueDomainService isTagNameUniqueDomainService;

    protected ChangeTagApplicationService(EventStore eventStore, IsTagNameUniqueDomainService isTagNameUniqueDomainService) {
        super(eventStore);
        this.isTagNameUniqueDomainService = isTagNameUniqueDomainService;
    }

    @Override
    public void execute(ExecutedCommand<ChangeTagCommand> command) {
        if (!isTagNameUniqueDomainService.isTagNameUnique(command.command().name())) {
            List<Event> events = eventStore.loadEvents(command.command().id());
            Tag tag = Tag.rehydrate(events);
            tag.change(command);
            eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
        } else {
            throw new DomainException(DomainException.Key.TagNameAlreadyExists);
        }
    }
}
