import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';

import { RecipeService } from '../recipe.service';
import { Recipe } from '../recipe.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {
  id: number;
  editMode = false;
  recipeForm: FormGroup;
  recipes: Recipe[] = [];
  recipe: Recipe;

  constructor(private route: ActivatedRoute,
              private recipeService: RecipeService,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (params: Params) =>  {
          this.id = +params['id'];
          this.editMode = params['id'] != null;
          this.initForm();
        }
      );
  }

  //adding to fix bug caused by new version of angular
  get controls() { // a getter!
    return (<FormArray>this.recipeForm.get('ingredients')).controls;
  }

  onSubmit(formData: Recipe): void {
    // const newRecipe = new Recipe(
    //   this.recipeForm.value['name'],
    //   this.recipeForm.value['description'],
    //   this.recipeForm.value['imagePath'],
    //   this.recipeForm.value['ingredients']);
    if (this.editMode) {
      this.recipeService.updateRecipe(this.id, this.recipeForm.value);
    } else {
      this.recipeService.addRecipe(formData).subscribe({
        next: (response: Recipe) => {
          console.log(response);
          // this.getRecipes();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
          this.recipeForm.reset()
        }, 
        complete: () => {
          console.log(this.recipes);
          this.recipeForm.reset();
        }

      });
    }
    this.onCancel();
  }

  onAddIngredient() {
    (<FormArray>this.recipeForm.get('ingredients')).push(
      new FormGroup({
        'name': new FormControl(null, Validators.required),
        'amount': new FormControl(null, Validators.required)
      })
    );
  }
  //  ^^^ amount Validators should be as follows, but pattern not working properly ^^^
  // 'amount': new FormControl(null, [
  //   Validators.required,
  //   Validators.pattern(/^[1-9]+[0-9]*$/)
  // ])

  onDeleteIngredient(index: number) {
    (<FormArray>this.recipeForm.get('ingredients')).removeAt(index);
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  private initForm() {
    let recipeName = '';
    let recipeImageUrl = '';
    let recipeDescription = '';
    let recipeIngredients = new FormArray([]);

    if (this.editMode) {
      this.recipeService.getRecipeById(this.id).subscribe({
        next: data => {
          recipeName = data.recipeName
          recipeImageUrl = data.recipeImageUrl;
          recipeDescription = data.recipeDescription;
          if (data['ingredients']) {
            for (let ingredient of data.recipeIngredients) {
              recipeIngredients.push(
                new FormGroup({
                  'name': new FormControl(ingredient.ingredientName, Validators.required),
                  'amount': new FormControl(ingredient.ingredientAmount, Validators.required)
                })
              );
            }
          }

        },
        error: (err: HttpErrorResponse) => {
          alert(err.message);
        },
        complete: () => {
        }
      })
    
      
      
      
      }
    }
    //  ^^^ amount Validators should be as follows, but pattern not working properly ^^^
    // 'amount': new FormControl(ingredient.amount, [
    //   Validators.required,
    //   Validators.pattern(/^[1-9]+[0-9]*$/)
    // ])

    // this.recipeForm = new FormGroup({
    //   'recipeName': new FormControl(recipeName, Validators.required),
    //   'recipeImageUrl': new FormControl(recipeImagePath, Validators.required),
    //   'reciipeDescription': new FormControl(recipeDescription, Validators.required),
    //   'recipeIngredients': recipeIngredients
    // });
}

