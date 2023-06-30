package animalResort;

public class Sloth implements ClimbingAnimal
{
    Sloth()
    {
    }

    @Override
    public void noise()
    {
        System.out.println("Yaaaawwwwwnnnnn");
    }

    @Override
    public void eat()
    {
        System.out.println("chew chew chew");
    }

    @Override
    public void climb()
    {
        System.out.println("climb climb climb");
    }
}
