package animalhouse.animals;

import animalhouse.Animal;

public class BelugaWhale extends Animal {
  // ***PROPERTIES***
  int maxDepth;

  // ***CONSTRUCTORS***
  public BelugaWhale() {
  }

  public BelugaWhale(boolean isMammal, int age, int legs, String name, int maxDepth) {
    super(isMammal, age, legs, name);
    this.maxDepth = maxDepth;
  }

  // ***GETTERS AND SETTERS***
  public int getMaxDepth() {
    return maxDepth;
  }

  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  // ***METHODS***
  public void spout() {
    System.out.println("Big splash!");
  }

  @Override
  public void eat() {
    System.out.println("Glub glub glub");
  }

  @Override
  public String toString() {
    return super.toString() + "as a BelugaWhale{" +
      "maxDepth=" + maxDepth +
      '}';
  }
}
