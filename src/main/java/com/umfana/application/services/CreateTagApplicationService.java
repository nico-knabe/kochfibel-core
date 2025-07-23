package com.umfana.application.services;

import com.umfana.application.eventstore.EventStore;
import com.umfana.domain.DomainException;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.services.IsTagNameUniqueDomainService;

import java.util.List;

public class CreateTagApplicationService extends ApplicationService<CreateTagCommand> {

    private final IsTagNameUniqueDomainService isTagNameUniqueDomainService;

    protected CreateTagApplicationService(EventStore eventStore, IsTagNameUniqueDomainService isTagNameUniqueDomainService) {
        super(eventStore);
        this.isTagNameUniqueDomainService = isTagNameUniqueDomainService;
    }

    @Override
    public void execute(ExecutedCommand<CreateTagCommand> command) {
        if (isTagNameUniqueDomainService.isTagNameUnique(command.command().name())) {
            Tag tag = new Tag(List.of());
            tag.create(command);
            eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
        } else {
            throw new DomainException(DomainException.Key.TagNameAlreadyExists);
        }
    }
}
