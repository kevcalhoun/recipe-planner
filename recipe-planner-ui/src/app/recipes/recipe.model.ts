import { Ingredient } from '../shared/ingredient.model';

export class Recipe {
    public recipeName: string;
    public recipeImageUrl: string;
    public recipeDescription: string;
    public recipeIngredients: Ingredient[];

    constructor(recipeName: string, recipeImageUrl: string, recipeDescription: string,  recipeIngredients: Ingredient[]) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeIngredients = recipeIngredients;
    }
}