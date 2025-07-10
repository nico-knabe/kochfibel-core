package com.umfana.application.services;

import com.umfana.application.eventstore.EventStore;
import com.umfana.domain.Command;
import com.umfana.domain.ExecutedCommand;

public abstract class ApplicationService<T extends Command> {

    protected final EventStore eventStore;

    protected ApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public abstract void execute(ExecutedCommand<T> command);
}
