package animalhouse.animals;

import animalhouse.Animal;
import animalhouse.interfaces.Domesticated;

public class Cat extends Animal implements Domesticated {
  // ***PROPERTIES***
  String pattern;

  // ***CONSTRUCTORS***
  public Cat() {}

  public Cat(boolean isMammal, int age, int legs, String name, String pattern) {
    super(isMammal, age, legs, name);
    this.pattern = pattern;
  }

  // ***GETTERS AND SETTERS***
  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  // ***METHODS***
  public void meow() {
    System.out.println("MEOWWWW");
  }

  @Override
  public String toString() {
    return super.toString() + " as a Cat{" +
      "pattern='" + pattern + '\'' +
      '}';
  }

  // ***INTERFACE METHODS***
  // Implemented from Domesticated
  // @Override
  // public void rollOver() {
  //   System.out.println("No");
  // }

  @Override
  public void backFlip() {
    System.out.println("Jump really high and don't forget to tuck the knees");
  }

  @Override
  public void cuddle() {
    System.out.println("PUUURRRRRRR");
  }
}
