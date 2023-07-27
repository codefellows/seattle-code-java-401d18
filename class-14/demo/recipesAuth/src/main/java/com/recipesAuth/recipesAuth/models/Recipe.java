package com.recipesAuth.recipesAuth.models;

import jakarta.persistence.*;

@Entity
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  String ingredients;
  String instructions;

  @ManyToOne
  SiteUser siteUser;

  protected Recipe() {}

  public Recipe(String ingredients, String instructions) {
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public String getIngredients() {
    return ingredients;
  }

  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public SiteUser getSiteUser() {
    return siteUser;
  }

  public void setSiteUser(SiteUser siteUser) {
    this.siteUser = siteUser;
  }

  @Override
  public String toString() {
    return "Recipe{" +
      "id=" + id +
      ", ingredients='" + ingredients + '\'' +
      ", instructions='" + instructions + '\'' +
      ", siteUser=" + siteUser +
      '}';
  }
}
