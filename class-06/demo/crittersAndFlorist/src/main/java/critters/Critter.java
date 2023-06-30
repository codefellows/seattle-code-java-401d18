package critters;

/**
 * An abstract critter class
 * It cannot be instantiated because it's abstract
 */
public abstract class Critter {

    /**
     * All implementing types have to have a makeNoise
     *
     * @return the noise the critter makes
     */
    public abstract String makeNoise();

    /**
     * A _Static_ helper method that creates a critter from a string
     *
     * @param critterTypeName the type of the critter
     * @return a new critter of the matching type
     * @throws BadCritterException if unrecognized critter type
     */
    public static Critter parse(String critterTypeName) throws BadCritterException {
        if (critterTypeName == "Cat") {
            return new Cat();
        } else if (critterTypeName == "Robot") {
            return new Robot();
        }

        // following the Integer.parseInt() style, we throw when not found
        // note:  Because this is a RuntimeException, we don't _have_ to have the "throws"
        // on the method name, but we do because it's good practice.
        // (It will show up when you check the docs for the method signature)
        throw new BadCritterException(critterTypeName + " is not a recognized critter.");
    }

}
