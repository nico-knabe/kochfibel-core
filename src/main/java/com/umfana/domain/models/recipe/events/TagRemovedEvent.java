package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class TagRemovedEvent extends RecipeEvent {
    public TagRemovedEvent(RecipeId id) {
        super(id);
    }
}
