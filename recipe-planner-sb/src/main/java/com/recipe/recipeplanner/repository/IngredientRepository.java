package com.recipe.recipeplanner.repository;

import com.recipe.recipeplanner.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    void deleteIngredientById(Long id);

    Optional<Ingredient> findIngredientById(Long aLong);
}
