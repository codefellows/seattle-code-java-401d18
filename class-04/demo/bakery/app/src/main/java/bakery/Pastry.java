package bakery;

public class Pastry
{
    boolean isIced;
    String flavor;
    boolean isVegan;
    int size;
    double weight;
    static String baker = "Shan";

    public Pastry(boolean isIced, String flavor, boolean isVegan, int size, double weight)
    {
        this.isIced = isIced;
        this.flavor = flavor;
        this.isVegan = isVegan;
        this.size = size;
        this.weight = weight;
    }
}
