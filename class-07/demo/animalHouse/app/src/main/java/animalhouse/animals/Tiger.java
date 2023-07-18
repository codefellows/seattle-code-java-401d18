package animalhouse.animals;

import animalhouse.Animal;

public class Tiger extends Animal {
  // ***PROPERTIES***
  int numStripes;

  // ***CONSTRUCTORS***
  public Tiger() {
  }

  public Tiger(boolean isMammal, int age, int legs, String name, int numStripes) {
    super(isMammal, age, legs, name);
    this.numStripes = numStripes;
  }

  // ***GETTERS AND SETTERS***
  public int getNumStripes() {
    return numStripes;
  }

  public void setNumStripes(int numStripes) {
    this.numStripes = numStripes;
  }

  // ***METHODS***
  public void growl() {
    System.out.println("Grrrrr");
  }

  @Override
  public void eat() {
    /**
     * since we don't do anything in this method other than call
     * the parent's eat() method, we don't NEED to include this method
     * in order to have the Animal class's eat() functionality
     **/
    super.eat();
  }

  @Override
  public String toString() {
    return super.toString() + " as a Tiger{" +
      "numStripes=" + numStripes +
      '}';
  }
}
