package com.umfana.domain.models.recipe.events;

import com.umfana.domain.models.recipe.RecipeId;

public class IngredientRemovedEvent extends RecipeEvent {
    public IngredientRemovedEvent(RecipeId recipeId) {
        super(recipeId);
    }
}
