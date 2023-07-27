package com.recipesAuth.recipesAuth.repos;

import com.recipesAuth.recipesAuth.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  public Recipe getRecipeByIngredients(String ingredients);
}
