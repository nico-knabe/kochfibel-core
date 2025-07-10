package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class RecipeAddedEvent extends RecipeEvent {
    public RecipeAddedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
