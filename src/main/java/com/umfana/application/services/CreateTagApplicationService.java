package com.umfana.application.services;

import com.umfana.domain.DomainException;
import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.TagEventStore;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.services.IsTagNameUniqueDomainService;

import java.util.List;

public class CreateTagApplicationService extends ApplicationService<CreateTagCommand> {

    private final TagEventStore eventStore;
    private final IsTagNameUniqueDomainService isTagNameUniqueDomainService;

    protected CreateTagApplicationService(TagEventStore eventStore, IsTagNameUniqueDomainService isTagNameUniqueDomainService) {
        this.eventStore = eventStore;
        this.isTagNameUniqueDomainService = isTagNameUniqueDomainService;
    }

    @Override
    public void execute(CreateTagCommand command) {
        if (isTagNameUniqueDomainService.isTagNameUnique(command.getName())) {
            Tag tag = new Tag(List.of());
            tag.handle(command);
            eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
        } else {
            throw new DomainException(DomainException.Key.TagNameAlreadyExists);
        }
    }
}
