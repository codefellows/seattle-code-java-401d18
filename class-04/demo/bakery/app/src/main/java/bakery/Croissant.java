package bakery;

public class Croissant extends Pastry
{
    public Croissant(boolean isIced,
                     String flavor,
                     boolean isVegan,
                     int size,
                     double weight,
                     boolean isHot,
                     boolean isFlaky)
    {
        super(isIced, flavor, isVegan, size, weight);
        this.isHot = isHot;
        this.isFlaky = isFlaky;
    }

    boolean isHot;
    boolean isFlaky;
}
