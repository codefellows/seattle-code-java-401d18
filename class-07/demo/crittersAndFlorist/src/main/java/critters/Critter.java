package critters;

public abstract class Critter {

    private String name;

    public Critter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
