package com.recipe.recipeplanner.service;

import com.recipe.recipeplanner.exception.RecipeNotFoundException;
import com.recipe.recipeplanner.model.Recipe;
import com.recipe.recipeplanner.repository.RecipeRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe by id " + id + " was not found" ));
    }

    public Recipe addRecipe(Recipe recipe) {
        recipe.setId(recipe.getId());
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteRecipeById(id);
    }
}
