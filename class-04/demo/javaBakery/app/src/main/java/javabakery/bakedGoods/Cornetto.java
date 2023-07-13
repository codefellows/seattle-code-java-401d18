package javabakery.bakedGoods;

public class Cornetto {
  private boolean isFilled;
  private boolean isSweet;
  private float price;
  private int calroies;
  private String size;

  public Cornetto() {
    this.isFilled = false;
    this.isSweet = false;
    this.price = 5.0f;
    this.calroies = 250;
    this.size = "medium";
  }

  public Cornetto(boolean isFilled, boolean isSweet) {
    this.isFilled = isFilled;
    this.isSweet = isSweet;
    this.price = 5.0f;
    this.calroies = 200;
    this.size = "small";
  }

  public Cornetto(boolean isFilled, boolean isSweet, float price, int calories, String size) {
    this.isFilled = isFilled;
    this.isSweet = isSweet;
    this.price = price;
    this.calroies = calories;
    this.size = size;
  }

  public void eat() {
    System.out.println("Nom nom nom");
  }

  public boolean isFilled() {
    return isFilled;
  }

  public void setFilled(boolean filled) {
    isFilled = filled;
  }

  public boolean isSweet() {
    return isSweet;
  }

  public void setSweet(boolean sweet) {
    isSweet = sweet;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    if (price < 10.0f) {
      this.price = price;
    }
  }

  public int getCalroies() {
    return calroies;
  }

  public void setCalroies(int calroies) {
    this.calroies = calroies;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "Cornetto{" +
      "isFilled=" + isFilled +
      ", isSweet=" + isSweet +
      ", price=" + price +
      ", calroies=" + calroies +
      ", size='" + size + '\'' +
      '}';
  }
}
