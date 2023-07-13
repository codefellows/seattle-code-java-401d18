package javabakery.bakedGoods;

public class Bearclaw {
  private String size;
  private boolean raisins;
  private boolean glazed;
  private float price;
  private int calories;

  public Bearclaw() {
  }

  public Bearclaw(String size, boolean raisins, boolean glazed, float price, int calories) {
    this.size = size;
    this.raisins = raisins;
    this.glazed = glazed;
    this.price = price;
    this.calories = calories;
  }

  public void eat() {
    System.out.println("bear noises");
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public boolean isRaisins() {
    return raisins;
  }

  public void setRaisins(boolean raisins) {
    this.raisins = raisins;
  }

  public boolean isGlazed() {
    return glazed;
  }

  public void setGlazed(boolean glazed) {
    this.glazed = glazed;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }
}
