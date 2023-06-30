package critters;

/**
 * The logger interface.  Anything that implements this has to implement the log function.
 */
public interface Logger {

    /**
     * Logs a message
     *
     * @param msg the message to be logged
     */
    void log(String msg);
}
