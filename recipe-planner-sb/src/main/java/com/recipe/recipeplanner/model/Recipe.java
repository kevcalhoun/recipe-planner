package com.recipe.recipeplanner.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

@Entity
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String recipeName;
    private String recipeImageUrl;
    private String recipeDescription;
    private List<Ingredient> recipeIngredients;

    public Recipe(String recipeName, String recipeImageUrl, String recipeDescription, List<Ingredient> recipeIngredients) {
        this.recipeName = recipeName;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public List<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", recipeImageUrl='" + recipeImageUrl + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", recipeIngredients='" + recipeIngredients + '\'' +
                '}';
    }


}
