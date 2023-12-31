/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javabakery;

import javabakery.bakedGoods.Cornetto;

import java.util.ArrayList;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

      Cornetto myNewCornetto = new Cornetto();
      System.out.println("default constructed cornetto size: " + myNewCornetto.getSize());

      Cornetto customCornetto = new Cornetto(true, true, 5.50f, 300, "large");
      System.out.println("custom constructed cornetto size: " + customCornetto.getSize());

      customCornetto.setPrice(6.0f);
      System.out.println("custom constructed cornetto price: " + customCornetto.getPrice());

      customCornetto.setPrice(12.0f);
      System.out.println("custom constructed cornetto price: " + customCornetto.getPrice());

      customCornetto.eat();

      // show public static final property
      System.out.println("All bakeries in the JavaBakery project open at " + Bakery.OPEN_TIME);

      // Make a bakery, add cornettos to it
      Bakery javaBakery = new Bakery();

      System.out.println("Cornettos before baked: " + javaBakery.getCornettoInventory());

      ArrayList<Cornetto> cornettos = new ArrayList<>();
      cornettos.add(myNewCornetto);
      cornettos.add(customCornetto);
      javaBakery.setCornettoInventory(cornettos);

      System.out.println("Cornettos after baked: " + javaBakery.getCornettoInventory());

      System.out.println("My javaBakery: " + javaBakery.toString());

      // print command line arguments
      for(int i = 0; i < args.length; i++) {
        System.out.println("Command line argument at index " + i + ": " + args[i]);
      }
    }
}
