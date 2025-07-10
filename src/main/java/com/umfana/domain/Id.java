package com.umfana.domain;

import java.util.UUID;

public class Id {

    private final UUID value;

    public Id(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
