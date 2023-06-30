package florist;

/**
 * Plants have a color and height
 * And they can grow.
 * <p>
 * It's an abstract class, so it can't be instantiated (no new Plant())
 */
public abstract class Plant {
    public String color;

    // Child classes can't refer to the private height
    private int height = 0;

    public Plant() {
        // the default constructor will set color to green
        this.color = "Green";
    }

    public Plant(String color) {
        // otherwise, it has to be specified
        this.color = color;
    }

    /**
     * Since height is private, this is the only way to see it
     * from outside of the Plant class.
     *
     * @return the height of the plant
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Simply increase the height of the plant
     */
    public void grow() {
        this.height++;
    }

}
