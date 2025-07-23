package com.umfana.application.services;

import com.umfana.application.eventstore.EventStore;
import com.umfana.domain.Event;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.tag.Tag;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;

import java.util.List;

public class DeleteTagApplicationService extends ApplicationService<DeleteTagCommand> {

    protected DeleteTagApplicationService(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    public void execute(ExecutedCommand<DeleteTagCommand> command) {
        List<Event> events = eventStore.loadEvents(command.command().id());
        Tag tag = new Tag(events);
        tag.delete(command);
        eventStore.saveEvents(tag.getId(), tag.getUncommittedEvents());
    }
}
