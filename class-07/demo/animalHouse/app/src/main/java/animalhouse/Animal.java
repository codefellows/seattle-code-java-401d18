package animalhouse;

public class Animal {
  /** change 'public' on previous line to `abstract` so that
   * we can define Animal parent class  without creating an instance
  **/

  // ***PROPERTIES***
  boolean isMammal;
  int age;
  int legs;
  String name;

  // ***CONSTRUCTORS***
  public Animal() {
  }

  public Animal(boolean isMammal, int age, int legs, String name) {
    this.isMammal = isMammal;
    this.legs = legs;
    this.name = name;
    this.age = age;
  }

  // ***GETTERS AND SETTERS***
  public boolean isMammal() {
    return isMammal;
  }

  public void setMammal(boolean mammal) {
    isMammal = mammal;
  }

  public int getLegs() {
    return legs;
  }

  public void setLegs(int legs) {
    this.legs = legs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  // ***METHODS***
  public void eat() {
    System.out.println("Nom nom nom");
  }

  @Override
  public String toString() {
    return "Animal{" +
      "isMammal=" + isMammal +
      ", legs=" + legs +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
