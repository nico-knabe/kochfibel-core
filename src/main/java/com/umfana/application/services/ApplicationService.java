package com.umfana.application.services;

import com.umfana.application.eventstore.EventStore;
import com.umfana.domain.Command;

public abstract class ApplicationService<T extends Command> {

    protected final EventStore eventStore;

    protected ApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public abstract void execute(T command);
}
