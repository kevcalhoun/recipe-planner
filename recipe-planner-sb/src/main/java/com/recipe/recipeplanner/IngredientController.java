package com.recipe.recipeplanner;


import com.recipe.recipeplanner.model.Ingredient;
import com.recipe.recipeplanner.model.Recipe;
import com.recipe.recipeplanner.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) { this.ingredientService = ingredientService; }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients () {
        List<Ingredient> ingredients = ingredientService.findAllIngredients();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.findIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.addIngredient((ingredient));
        return new ResponseEntity<>(newIngredient, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientService.updateIngredient(ingredient);
        return new ResponseEntity<>(updateIngredient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable("id") Long id) {
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
