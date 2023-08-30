package com.recipe.recipeplanner.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String ingredientName;
    private Number ingredientQuantity;

    public Ingredient(String ingredientName, Number ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Number getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Number ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientQuantity='" + ingredientQuantity + '\'' +
                '}';
    }
}
