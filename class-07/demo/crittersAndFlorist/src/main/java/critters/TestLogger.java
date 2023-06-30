package critters;

import java.util.ArrayList;
import java.util.List;

/**
 * This test logger just stores the messages.
 * This allows them to be checked later during a test!
 */
public class TestLogger implements Logger {

    /**
     * The list of messages logged
     */
    public List<String> logged = new ArrayList<>();

    @Override
    public void log(String msg) {
        // just add them to the list
        this.logged.add(msg);
    }
}
