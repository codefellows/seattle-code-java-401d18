package critters;

/**
 * Robot extends Critter and implements two different interfaces!
 * <p>
 * You can only extend one class, but can implement many interfaces.
 */
public class Robot extends Critter implements NoiseMaker, Dancer {

    public int timesBooted = 0;

    /**
     * Critter has a required constructor.
     * This is the implementation
     *
     * @param name
     */
    public Robot(String name) {
        super(name);
    }

    /**
     * A robot only function = how many times has it rebooted
     */
    public void reboot() {
        timesBooted++;
    }

    /**
     * The NoiseMaker.makeNoise implementation
     *
     * @return
     */
    @Override
    public String makeNoise() {
        return "RRRRRROBOT NOISES, BOOTED: " + timesBooted;
    }

    /**
     * The Dancer.getDance() implementation
     *
     * @return
     */
    @Override
    public String getDance() {
        return "DO THE ROBOT.";
    }

    /**
     * The Dancer.getBetterDance() implementation
     *
     * @return
     */
    @Override
    public String getBetterDance() {
        return "Do the lawnmower.";  // arguably not-better
    }
}
