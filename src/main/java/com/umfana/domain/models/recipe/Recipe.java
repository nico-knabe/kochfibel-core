package com.umfana.domain.models.recipe;

import com.umfana.domain.models.recipe.valueobjects.Category;
import com.umfana.domain.models.recipe.valueobjects.IngredientUsage;
import com.umfana.domain.models.recipe.valueobjects.Step;

import java.util.List;
import java.util.UUID;

public class Recipe {
    private final RecipeId id;
    private String title;
    private int servings;
    private List<Step> instructions;
    private List<IngredientUsage> ingredients;
    private Category category;

    private Recipe(RecipeId id, String title, int servings, List<Step> instructions, List<IngredientUsage> ingredients, Category category) {
        this.id = id;
        this.title = title;
        this.servings = servings;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
    }

    public static Recipe writeDownRecipe(String title, int servings, List<Step> instructions, List<IngredientUsage> ingredients, Category category) {
        return new Recipe(new RecipeId(UUID.randomUUID()), title, servings, instructions, ingredients, category);
    }

    public void changeRecipe(UUID id, String title, int servings, List<Step> instructions, List<IngredientUsage> ingredients, Category category) {
        this.title = title;
        this.servings = servings;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
    }
}