package com.edy.buymorestuff.model;

import androidx.annotation.NonNull;

public enum ProductCategoryEnum
{
  CLOTHES("Clothes"),
  ELECTRONICS("Electronics"),
  PERISHABLE_GOODS("Perishable Goods"),
  OFFICE_SUPPLIES("Office Supplies"),
  MISC("Miscellaneous");

  private final String productText;

  ProductCategoryEnum(String productText)
  {
    this.productText = productText;
  }

  public String getProductText() {
    return this.productText;
  }

  public static ProductCategoryEnum fromString(String possibleProductText) {
    for (ProductCategoryEnum product : ProductCategoryEnum.values())
    {
      if (product.productText.equals(possibleProductText))
      {
        return product;
      }
    }

    return null;
  }

  @NonNull
  @Override
  public String toString()
  {
    if (productText == null)
    {
      return "";
    }
    return productText;
  }
}
