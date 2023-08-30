import { Injectable } from '@angular/core';

import { Recipe } from './recipe.model';
import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from '../shopping-list/shopping-list.service';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable()
export class RecipeService {
    private apiServerUrl = environment.apiBaseUrl;
    recipesChanged = new Subject<Recipe[]>();

    // private recipes: Recipe[] = [
    //     new Recipe(
    //         'A Test Recipe', 
    //         'This is simply a test', 
    //         'https://www.saveur.com/uploads/2020/11/20/Y7RZPFZEERAZVHJ2VHC2RXMEEY.jpg',
    //         [
    //             new Ingredient('Meat', 1),
    //             new Ingredient('French Fries', 2)
    //         ]),
    //     new Recipe(
    //         'Another Test Recipe', 
    //         'This is simply a test', 
    //         'https://www.saveur.com/uploads/2020/11/20/Y7RZPFZEERAZVHJ2VHC2RXMEEY.jpg',
    //         [
    //             new Ingredient('Buns', 2),
    //             new Ingredient('Meat', 4)
    //         ])
    // ];
    private recipes: Recipe[] = [];

    constructor(private slService: ShoppingListService,
        private http: HttpClient) {}

    setRecipes(recipes: Recipe[]) {
        this.recipes = recipes;
        this.recipesChanged.next(this.recipes.slice());
    }

    getRecipes(): Observable<Recipe[]> {
        // return this.recipes.slice();
        return this.http.get<Recipe[]>(`${this.apiServerUrl}/recipe/all`);
    }

    getRecipeById(index: number): Observable<Recipe> {
        return this.http.get<Recipe>(`${this.apiServerUrl}/recipe/find/${index}`);
    }

    addIngredientsToShoppingList(ingredients: Ingredient[]) {
        // this.slService.addIngredients(ingredients);
        this.slService.addIngredients(ingredients)

    }

    addRecipe(recipe: Recipe): Observable<Recipe> {
        // this.recipes.push(recipe);
        // this.recipesChanged.next(this.recipes.slice());
        return this.http.post<Recipe>(`${this.apiServerUrl}/recipe/add`, recipe);
    }

    updateRecipe(index: number, newRecipe: Recipe) {
        this.recipes[index] = newRecipe;
        this.recipesChanged.next(this.recipes.slice());
    }

    deleteRecipe(recipeId: number) {
        // this.recipes.splice(index, 1);
        // this.recipesChanged.next(this.recipes.slice());
        return this.http.delete<void>(`${this.apiServerUrl}/recipe/delete/${recipeId}`);
    }
   

}