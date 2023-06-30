package com.ferreirae.demo;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Dinosaur {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;
    public String species;
    public int numberOfSpikes;
    public String diet;

    @ManyToOne
    public Location location;

    @ManyToMany
    @JoinTable(
            name = "dinosaur_likes",
            joinColumns = { @JoinColumn(name = "liker_id") },
            inverseJoinColumns = { @JoinColumn(name = "likee_id") }
    )
    public Set<Dinosaur> dinosThatILike;

    @ManyToMany(mappedBy = "dinosThatILike")
    public Set<Dinosaur> dinosThatLikeMe;

    public Dinosaur() {}

    public Dinosaur(String species,int numberOfSpikes, String diet) {
        this.species = species;
        this.numberOfSpikes = numberOfSpikes;
        this.diet = diet;
    }

    public String toString() {
        return species + " dinosaur with " + numberOfSpikes + " spikes, in " + (this.location != null ? this.location.name : "no location") + ", with " + dinosThatLikeMe.size() + " dinos that like me and " + dinosThatILike.size() + " that I like" ;
    }
}
