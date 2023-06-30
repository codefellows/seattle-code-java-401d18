package animalResort;

public class Bear implements Animal
{
    boolean isFurry = true;

    Bear()
    {
    }

    @Override
    public void noise()
    {
        System.out.println("RARRRRR");
    }

    @Override
    public void eat()
    {
        System.out.println("Om nom nom");
    }

    public void makePicnicBasket()
    {
        System.out.println("Here is a pic-a-nic basket");
    }
}
