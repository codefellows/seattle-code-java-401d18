package critters;

public class Cat extends Critter {

    /**
     * The cat class has one method to meow!
     *
     * @return a noise that a cat makes
     */
    public String meow() {
        return "MEOW (feed me)";
    }

    /**
     * The makeNoise function is an implementation of the abstract class
     *
     * @return the cat making noise
     */
    @Override
    public String makeNoise() {
        // we're just returning the meow() results
        // we _could_ have renamed meow, but what if another framework depended on it?
        // (This is a design decision:  is it safe to rename?  Is it better?)
        // (In this case, it would have been better to rename ;))
        return meow();
    }
}
