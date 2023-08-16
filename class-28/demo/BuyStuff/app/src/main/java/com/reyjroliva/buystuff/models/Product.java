package com.reyjroliva.buystuff.models;

// TODO: Step 2-1: Create a data class
public class Product {
  String name;

  public Product(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
