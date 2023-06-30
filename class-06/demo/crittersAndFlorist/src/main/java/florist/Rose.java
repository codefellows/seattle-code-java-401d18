package florist;

/**
 * Extends flowers, adds some thorns!
 */
public class Rose extends Flower {

    public int numThorns;

    public Rose() {
        // a rose is always named Rose, and always color Red
        // super has to be called first in constructors
        super("Rose", "Red");

        // set a default
        this.numThorns = 10;
    }

    /**
     * When rose.grow() is called, it will also increase the number of thorns.
     */
    @Override
    public void grow() {
        // increase the thorns
        this.numThorns++;

        // then the height.
        // super doesn't _have_ to be first in method overrides
        // (See toString example)
        super.grow();
    }

    @Override
    public String toString() {
        // you can get the results of the super
        String oldToString = super.toString();

        // and then use that make decisions or modify it
        return "MyString: " + oldToString;
    }

    /**
     * The implementation of getBloomHeight
     *
     * @return Always returns 3;
     */
    @Override
    public int getBloomHeight() {
        return 3;
    }
}
