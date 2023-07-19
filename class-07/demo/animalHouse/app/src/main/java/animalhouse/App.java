/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalhouse;

import animalhouse.animals.Axolotl;
import animalhouse.animals.Cat;

import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    Axolotl wasim = new Axolotl(false, 15, 4, 10, "yellow", "Wasim");
    System.out.println(wasim.toString());

    wasim.setMammal(true);

    System.out.println(wasim);

    wasim.eat(); // <- overridded with Axolotl eat() method
    wasim.regenerate();
    wasim.regenerate(2); // <- calls overloaded version of the regenerate() method

    ArrayList<Animal> animalHouse = new ArrayList<>();
    animalHouse.add(wasim);
    animalHouse.add(new Axolotl());
    animalHouse.add(new Axolotl());

    // treat all anmials in list as Animal class
    for(Animal animal : animalHouse) {
      animal.eat();
    }

    Animal myAnimal = new Axolotl();
    Axolotl rey = new Axolotl();
    Animal reyAnimal = (Animal)rey; // <- inline type casting

    System.out.println("how many owners does a cat REALLY have: " + Cat.NUMBER_OF_OWNERS);

    Cat indi = new Cat(true, 2, 4, "Indi", "black");
    indi.rollOver();
    indi.backFlip();
    indi.cuddle();
    indi.meow();
  }
}