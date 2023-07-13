package javabakery.bakedGoods;

public class Danish {
  private String size;
  private int calories;
  private float price;
  private String filling;
  private boolean isGlazed;

  public Danish() {
  }

  public Danish(String size, int calories, float price, String filling, boolean isGlazed) {
    this.size = size;
    this.calories = calories;
    this.price = price;
    this.filling = filling;
    this.isGlazed = isGlazed;
  }

  public void eat() {
    System.out.println("Nom nom nom nom nom");
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getFilling() {
    return filling;
  }

  public void setFilling(String filling) {
    this.filling = filling;
  }

  public boolean isGlazed() {
    return isGlazed;
  }

  public void setGlazed(boolean glazed) {
    isGlazed = glazed;
  }

  @Override
  public String toString() {
    return "Danish{" +
      "size='" + size + '\'' +
      ", calories=" + calories +
      ", price=" + price +
      ", filling='" + filling + '\'' +
      ", isGlazed=" + isGlazed +
      '}';
  }
}
