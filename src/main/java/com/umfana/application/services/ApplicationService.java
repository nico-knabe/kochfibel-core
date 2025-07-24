package com.umfana.application.services;

import com.umfana.domain.Command;

public abstract class ApplicationService<T extends Command> {

    public abstract void execute(T command);
}
