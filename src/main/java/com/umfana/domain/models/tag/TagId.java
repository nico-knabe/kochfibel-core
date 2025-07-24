package com.umfana.domain.models.tag;

import com.umfana.domain.Id;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TagId extends Id {
    public TagId(UUID value) {
        super(value);
    }
}
