package florist;

public class Tulip extends Flower {
    public int bulbSize = 1;

    public Tulip(String name) {
        // tulip takes a name, but is always yellow in color
        super(name, "Yellow");
    }

    @Override
    public int getBloomHeight() {
        return 4;
    }
}
