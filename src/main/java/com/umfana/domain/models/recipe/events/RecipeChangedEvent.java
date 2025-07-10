package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class RecipeChangedEvent extends RecipeEvent {
    public RecipeChangedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
