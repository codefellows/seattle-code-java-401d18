package demo;

/**
 * The Critter class is the same "Shape" as the Person class
 * So it can load the same data from JSON
 */
public class Critter {
    public String name;
    public int age;
    public String[] favoriteThings;

    /**
     * Deprecated functions show up with a strike through
     * and cause warnings when building
     * <p>
     * The method won't cause any problems during serialization with gson!
     * It will just be ignored.
     */
    @Deprecated
    public void sayHi() {
        System.out.println("Hi");
    }
}
