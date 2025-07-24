package com.umfana.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Id {

    private final UUID value;

    public Id(UUID value) {
        this.value = value;
    }

}
