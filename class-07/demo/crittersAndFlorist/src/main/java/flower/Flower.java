package flower;

/**
 * An abstract of flower.
 * It has a bloom status (but no logic around it)
 * <p>
 * And a height, that can be increased by using grow()
 */
public abstract class Flower {
    public int height = 0;

    private boolean isBloomed;

    private String name;

    public Flower() {
        this.height = 1;
    }

    public void grow() {
        this.height++;
    }

    public boolean isBloomed() {
        return isBloomed;
    }

    // protected!  Only package items can setBloomed status
    // that includes Child classes (Classes that extend this one)
    protected void setBloomed(boolean bloomed) {
        isBloomed = bloomed;
    }

    public String getName() {
        return name;
    }

    // because this is commented out, name is readonly
//    public void setName(String name) {
//        this.name = name;
//    }
}
