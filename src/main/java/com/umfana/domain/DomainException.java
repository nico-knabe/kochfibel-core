package com.umfana.domain;

public final class DomainException extends RuntimeException {


    public enum Key {
        IngredientAlreadyExists,
        IngredientDoesNotExists,
        TagNameDoesNotBeBlank,
        TagNameAlreadyExists,
        CouldNotCreateTag,
        CouldNotChangeTag,
        CouldNotDeleteTag,
        TagIsDeleted,
        TagColorDoesNotBeNull,
        WrongTagId
    }

    private final Key description;

    public DomainException(Key description) {
        this.description = description;
    }

    public Key getDescription() {
        return description;
    }
}