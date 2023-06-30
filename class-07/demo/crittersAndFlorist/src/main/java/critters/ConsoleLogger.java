package critters;

/**
 * An implementation of the logger.
 * <p>
 * It outputs all messages to sout
 */
public class ConsoleLogger implements Logger {

    /**
     * Implementing an interface looks just like
     * implementing an abstract method from a parent class.
     * <p>
     * In intellij there will be a helper icon that can take you to
     * the interface on the <=== left.
     *
     * @param msg the message to be logged
     */
    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}
