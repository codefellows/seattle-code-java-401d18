package critters;

// extend Exception to create your own exceptions
// if you extend RuntimeException, you don't _have_ to catch it.
public class BadCritterException extends RuntimeException {

    /**
     * Default constructor creates the exception with no additional information
     */
    public BadCritterException() {
        super();
    }

    /**
     * This exception allows you to set a message in the error
     *
     * @param message the message to show
     */
    public BadCritterException(String message) {
        // calling super with the message
        // passes the message to the default exception message handling
        super(message);
    }
}
