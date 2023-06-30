package unicorns;

import java.util.ArrayList;

public class Unicorn
{
    ArrayList<String> colors;
    int hornLength;
    boolean hasWings;
    String name;

    public Unicorn(ArrayList<String> colors, int hornLength, boolean hasWings, String name)
    {
        this.colors = colors;
        this.hornLength = hornLength;
        this.hasWings = hasWings;
        this.name = name;
    }
}
