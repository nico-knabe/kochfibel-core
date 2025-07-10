package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class RecipeRemovedEvent extends RecipeEvent {
    public RecipeRemovedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
