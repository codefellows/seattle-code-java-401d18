package critters;

/**
 * Unlike the robot, the cat only implements one interface
 */
public class Cat extends Critter implements NoiseMaker {

    /**
     * The default constructor
     *
     * @param name
     */
    public Cat(String name) {
        super(name);
    }

    /**
     * A cat only method
     */
    public void pet() {
        // pretend we're petting a cat.
    }

    /**
     * Implementation of NoiseMaker.makeNoise
     *
     * @return
     */
    @Override
    public String makeNoise() {
        return "meow, please pet me";
    }
}
