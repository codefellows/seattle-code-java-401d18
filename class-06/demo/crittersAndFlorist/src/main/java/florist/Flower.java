package florist;

/**
 * Flower is another abstract that extends plant!
 * <p>
 * You still can't do new Flower();
 */
public abstract class Flower extends Plant {

    // the bloom status of the flower
    private boolean isBloomed = false;

    // name of our flower
    public String name;

    /**
     * Because this is the only constructor,
     * flowers have to have a name and color
     *
     * @param name
     * @param color
     */
    public Flower(String name, String color) {
        super(color);
        this.name = name;
    }

    /**
     * This is used to determine when a flower will bloom
     * <p>
     * But must be implemented by the extending class
     *
     * @return at what height the flower will bloom.
     */
    public abstract int getBloomHeight();

    public boolean isBloomed() {
        return isBloomed;
    }

    public void setBloomed(boolean bloomed) {
        isBloomed = bloomed;
    }

    /**
     * Overrides the default behavior to add bloom functionality.
     * <p>
     * Note, this one is _also_ overriden in rose!
     */
    @Override
    public void grow() {
        // calls the parent (Plant) class grow to increase height.
        super.grow();

        // now, compare the height to the implementation of getBloomHeight
        // and set bloom if it's greater than equal
        if (this.getHeight() >= this.getBloomHeight()) {
            this.setBloomed(true);
        }
    }
}
