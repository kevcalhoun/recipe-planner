package com.recipe.recipeplanner.service;

import com.recipe.recipeplanner.exception.IngredientNotFoundException;
import com.recipe.recipeplanner.exception.RecipeNotFoundException;
import com.recipe.recipeplanner.model.Ingredient;
import com.recipe.recipeplanner.model.Recipe;
import com.recipe.recipeplanner.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) { this.ingredientRepository = ingredientRepository; }

    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findIngredientById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient by id " + id + " was not found" ));
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        ingredient.setId(ingredient.getId());
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }


    public Ingredient updateIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteIngredientById(id);
    }
}
