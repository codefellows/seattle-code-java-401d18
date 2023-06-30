package flower;

public class Daisy extends Flower {

    /**
     * Daisy overrides Flower, and implements some setBloomed logic
     */
    @Override
    public void grow() {
        // this will call the Flower.grow() which will increase the height
        super.grow();

        if (this.height > 2) {
            // this will setBloomed if height is greater than 2.
            // you can't setBloomed from outside of the package!
            this.setBloomed(true);
        }
    }

    /**
     * Only a daisy can be a daisy
     */
    public void beADaisy() {
        System.out.println("daisy if you do");
    }
}
