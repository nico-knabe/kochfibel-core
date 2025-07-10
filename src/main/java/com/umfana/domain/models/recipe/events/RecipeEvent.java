package com.umfana.domain.models.recipe.events;

import com.umfana.domain.Event;
import com.umfana.domain.models.recipe.RecipeId;

public abstract class RecipeEvent extends Event {

    private final RecipeId recipeId;

    public RecipeEvent(RecipeId recipeId) {
        this.recipeId = recipeId;
    }

    public RecipeId getRecipeID() {
        return recipeId;
    }
}
