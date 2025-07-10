package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class TagAddedEvent extends RecipeEvent {
    public TagAddedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
