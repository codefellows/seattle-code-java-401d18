package com.reyjroliva.buystuff.models;

import java.util.Date;

public class Product {
  String name;
  String description;
  java.util.Date dateCreated;
  ProductCategoryEnum category;

  public Product(String name, String description, java.util.Date dateCreated, ProductCategoryEnum category) {
    this.name = name;
    this.description = description;
    this.dateCreated = dateCreated;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public ProductCategoryEnum getCategory() {
    return category;
  }

  public void setCategory(ProductCategoryEnum category) {
    this.category = category;
  }
}
