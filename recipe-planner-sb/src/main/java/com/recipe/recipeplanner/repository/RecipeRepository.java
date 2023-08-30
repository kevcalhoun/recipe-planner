package com.recipe.recipeplanner.repository;

import com.recipe.recipeplanner.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    void deleteRecipeById(Long id);

    Optional<Recipe> findRecipeById(Long id);
}
