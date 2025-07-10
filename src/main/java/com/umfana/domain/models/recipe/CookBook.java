package com.umfana.domain.models.recipe;

import java.util.*;

public class CookBook {

    private final IngredientsCatalog ingredientsCatalog;
    private final Map<UUID, Recipe> recipes;

    private CookBook() {
        this.recipes = new HashMap<>();
        this.ingredientsCatalog = IngredientsCatalog.createIngredientsCatalog();
    }

    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes.values().stream().toList());
    }

    public Recipe getRecipe(UUID recipeId) {
        return recipes.get(recipeId);
    }
}
