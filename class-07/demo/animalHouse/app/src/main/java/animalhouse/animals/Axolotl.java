package animalhouse.animals;

import animalhouse.Animal;

public class Axolotl extends Animal {
  // ***PROPERTIES***
  int gills;
  String color;

  // ***CONSTRUCTORS***
  public Axolotl() {
    super();
  }

  public Axolotl(boolean isMammal, int age, int legs, int gills, String color, String name) {
    super(isMammal, age, legs, name);
    this.gills = gills;
    this.color = color;
  }

  // ***GETTERS AND SETTERS***
  public int getGills() {
    return gills;
  }

  public void setGills(int gills) {
    this.gills = gills;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  // ***METHODS***
  public void swim() {
    System.out.println("Splish Splash I'm an Axolotl");
  }

  public void regenerate() {
    System.out.println("All my limbs are back!");
  }

  public void regenerate(int limbs) {
    System.out.println("I've regenerated " + limbs + " of my limbs!");
  }

  @Override // needed because eat is defined on parent class Animal
  public void eat() {
    System.out.println("Glub glub glub");
  }

  @Override
  public String toString() {
    return super.toString() + " as an Axolotl{" +
      "gills=" + gills +
      ", color='" + color + '\'' +
      '}';
  }
}
