package com.umfana.application.services;

import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.TagEvent;
import com.umfana.domain.models.tag.TagEventStore;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;

import java.util.List;

public class DeleteTagApplicationService extends ApplicationService<DeleteTagCommand> {

    private final TagEventStore eventStore;

    protected DeleteTagApplicationService(TagEventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteTagCommand command) {
        List<TagEvent> events = eventStore.loadEvents(command.getId());
        Tag tag = new Tag(events);
        tag.handle(command);
        eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
    }
}
