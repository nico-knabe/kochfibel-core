package com.umfana.domain.services;

import com.umfana.domain.DomainService;

public interface IsTagNameUniqueDomainService extends DomainService {
    boolean isTagNameUnique(String tagName);
}
