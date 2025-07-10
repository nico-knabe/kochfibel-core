package com.umfana.domain.models.recipe;

import com.umfana.domain.DomainException;
import com.umfana.domain.models.recipe.valueobjects.Ingredient;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IngredientsCatalog {

    private final UUID id;
    private final Set<Ingredient> ingredients = new HashSet<>();

    private IngredientsCatalog(UUID id) {
        this.id = id;
    }

    public static IngredientsCatalog createIngredientsCatalog() {
        return new IngredientsCatalog(UUID.randomUUID());
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            throw new DomainException(DomainException.Key.IngredientAlreadyExists);
        }
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            throw new DomainException(DomainException.Key.IngredientDoesNotExists);
        }
        ingredients.remove(ingredient);
    }
}
