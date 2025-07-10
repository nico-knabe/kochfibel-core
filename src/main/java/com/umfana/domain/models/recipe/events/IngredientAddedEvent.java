package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class IngredientAddedEvent extends RecipeEvent {
    public IngredientAddedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
