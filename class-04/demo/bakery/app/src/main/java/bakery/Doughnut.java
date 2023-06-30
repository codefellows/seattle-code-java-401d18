package bakery;

public class Doughnut extends Pastry
{
    boolean isCake;

    public Doughnut(boolean isIced,
                    String flavor,
                    boolean isVegan,
                    int size,
                    double weight,
                    boolean isCake)
    {
        super(isIced, flavor, isVegan, size, weight);
        this.isCake = isCake;
    }

    @Override
    public String toString()
    {
        String doughnut = "isIced: " + isIced + "\nflavor: " + flavor + "\nisVegan: " + isVegan
                + "\nsize: " + size + "\nweight: " + weight + "\nisCake: " + isCake + "\nbaker: " + baker;

        String doughnut2 = String.format("isIced: %s\n flavor: %b\nisVegan: %b\nsize: %d\nweight: %f\nisCake: %b",
                this.isIced, this.flavor, this.isVegan, this.size, this.weight, this.isCake);

        return doughnut;
    }
}
