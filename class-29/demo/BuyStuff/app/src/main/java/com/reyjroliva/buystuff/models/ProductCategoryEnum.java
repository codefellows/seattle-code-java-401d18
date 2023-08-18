package com.reyjroliva.buystuff.models;

import androidx.annotation.NonNull;

public enum ProductCategoryEnum {
  CLOTHES("Clothes"),
  ELECTRONICS("Electronics"),
  PERISHABLE_GOODS("Perishable Goods"),
  OFFICE_SUPPLIES("Office Supplies"),
  MISC("Miscellaneous");

  private String text;

  ProductCategoryEnum(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public static ProductCategoryEnum fromString(String text) {
    for (ProductCategoryEnum pce : ProductCategoryEnum.values()) {
      if (pce.text.equalsIgnoreCase(text)) {
        return pce;
      }
    }
    return null;
  }
  
  @NonNull
  @Override
  public String toString() {
    return this.text;
  }
}
