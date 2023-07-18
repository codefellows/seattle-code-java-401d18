package animalhouse.interfaces;

public interface Domesticated {
  // Any property in an interface must be static final
  public static final int NUMBER_OF_OWNERS = 1;

  // Default methods in interfaces are optional to override
  public default void rollOver() {
    System.out.println("Lay on the ground adn turn your body around");
  }

  // Below must be implemented in implementing classes... public is part of the interface as a whole
  void backFlip();

  public void cuddle();
}
