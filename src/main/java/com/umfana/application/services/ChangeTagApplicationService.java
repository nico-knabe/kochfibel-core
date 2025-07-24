package com.umfana.application.services;

import com.umfana.domain.DomainException;
import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagEventStore;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.services.IsTagNameUniqueDomainService;

import java.util.List;

public class ChangeTagApplicationService extends ApplicationService<ChangeTagCommand> {
    private final TagEventStore eventStore;
    private final IsTagNameUniqueDomainService isTagNameUniqueDomainService;

    protected ChangeTagApplicationService(TagEventStore eventStore, IsTagNameUniqueDomainService isTagNameUniqueDomainService) {
        this.eventStore = eventStore;
        this.isTagNameUniqueDomainService = isTagNameUniqueDomainService;
    }

    @Override
    public void execute(ChangeTagCommand command) {
        if (!isTagNameUniqueDomainService.isTagNameUnique(command.getName())) {
            List<TagEvent> events = eventStore.loadEvents(command.getId());
            Tag tag = new Tag(events);
            tag.handle(command);
            eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
        } else {
            throw new DomainException(DomainException.Key.TagNameAlreadyExists);
        }
    }
}
