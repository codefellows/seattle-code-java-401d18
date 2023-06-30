package flower;

import critters.Logger;

public class Rose extends Flower implements Logger {

//    public Rose() {
//        this.height = 5;
//    }

    /**
     * The only constructor
     * Because we don't have a Rose(), height is required!
     *
     * @param height
     */
    public Rose(int height) {
        super();
        this.height = height;
    }

    /**
     * A private method.  It can only be called from within Rose
     */
    private void doSomethingNeato() {
        System.out.println("Neato again");
    }

    /**
     * A rose only method
     */
    public void beARose() {
        this.doSomethingNeato();
        System.out.println("Throw diamond into ocean");
    }

    /**
     * Our rose could be passed int to the NoiseMakerOutputService!
     *
     * @param msg the message to be logged
     */
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}
