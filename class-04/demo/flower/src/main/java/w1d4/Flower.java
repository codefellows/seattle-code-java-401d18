package w1d4;

/**
 * A happy flower.
 */
public class Flower {

    /**
     * This is the name of our flower.
     * <p>
     * It's publicly accessible, so can be modified directly
     * outside of the class.
     */
    public String name;

    /**
     * This is the current height of the flower
     * <p>
     * Because it's private, only this class can change it.
     */
    private int height;

    /**
     * Has this flower bloomed?
     * <p>
     * note:  "isSomething" naming convention for booleans
     * <p>
     * Also private.
     */
    private boolean isBloomed = false;

    /**
     * A public static value
     * <p>
     * This can be changed from outside of the class like:
     * Flower.chopHeight = 10;
     */
    public static int chopHeight = 1;

    /**
     * static + final = constant
     * <p>
     * These are named using UPPER_SNAKE_CASE
     * <p>
     * Math.PI, Integer.MAX are examples
     * <p>
     * Use when you want to store constant values.
     */
    public static final int CHOP_HEIGHT = 1;

    /**
     * A constructor that sets the name of the flower
     *
     * @param name The name of the flower
     */
    public Flower(String name) {
        this.name = name;
    }

    /**
     * A constructor that sets the name and height of the flower.
     *
     * @param name   The name of the flower
     * @param height The starting height of the flower
     */
    public Flower(String name, int height) {
        // this() lets you call other constructors
        // it must be the first call
        this(name);

        // because the class variable 'height'
        // has the same name as the parameter
        // we have to use this.height = height
        this.height = height;
    }

    /**
     * The accessor (getter) to get the height.
     *
     * @return the current height of the flower
     */
    public int getHeight() {
        return height;
    }

    /**
     * By not having a setter, height is read-only
     */
//    public void setHeight(int height) {
//        this.height = height;
//    }

    /**
     * An action to make the flower bigger.
     * <p>
     * This is the only way to increase the height!
     */
    public void grow() {
        height++;
    }

    /**
     * Makes the flower bloom.
     * <p>
     * If the flower has already bloomed, will not change.
     */
    public void bloom() {
        this.isBloomed = true;
    }

    /**
     * Has the flower bloomed?  Again, note the naming convention.
     *
     * @return the bloomed status of the flower.
     */
    public boolean isBloomed() {
        return isBloomed;
    }

    /**
     * Overriding the 'toString()' lets you have a 'friendly'
     * output for your class for things like System.out.print(flower);
     * <p>
     * Will also be used for String results = "Hi: " + flower;
     *
     * @return The string representation of the flower
     */
    @Override
    public String toString() {
        return "I'm a flower and my name is: " + name;
    }

    /**
     * This a static method.  You can't access flower properties
     * or methods via this.  Instead, you have to pass it in.
     * <p>
     * Chops the specified flower to the Flower.chopHeight
     * and removes the bloom!
     *
     * @param flower The Flower to be modified
     */
    public static void chop(Flower flower) {
        // Static methods can still access private variables!
        // If this static method was in a different class
        // it wouldn't be able to.
        flower.isBloomed = false;
        flower.height = chopHeight;
    }
}
