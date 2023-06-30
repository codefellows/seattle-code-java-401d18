package bakery;

public class Cake extends Pastry
{
    int numberOfLayers;

    public Cake(boolean isIced,
                String flavor,
                boolean isVegan,
                int size,
                double weight,
                int numberOfLayers)
    {
        super(isIced, flavor, isVegan, size, weight);
        this.numberOfLayers = numberOfLayers;
    }
}
