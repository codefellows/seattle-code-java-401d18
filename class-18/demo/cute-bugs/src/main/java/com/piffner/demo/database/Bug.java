package com.piffner.demo.database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bug {

    @Id
    @GeneratedValue
    public long id;

    // Many bugs have a location
    // By adding this annotation, we tell hibernate/spring
    // to create the relationship
    // (The opposite is on the Location)
    @ManyToOne
    public Location location;


    // Option one for Adding a self join
    // Use the "JoinTable" and describe it
    // this is likely more performant than the second option

//    @ManyToMany
//    @JoinTable(
//            name = "bug_like",
//            joinColumns = {@JoinColumn(name = "from_id")},
//            inverseJoinColumns = {@JoinColumn(name = "to_id")}
//    )
//    public List<Bug> likes;
//
//    @ManyToMany(mappedBy = "likes", fetch = FetchType.EAGER)
//    public List<Bug> likers;

    // Option two is to create a model to represent that mapping
    @OneToMany(mappedBy = "fromBug")
    public List<BugLike> likes;

    @OneToMany(mappedBy = "toBug")
    public List<BugLike> likers;

    public String name;
    public int numberOfLegs;
    public int cutenessScale;
    public String typeOfBug;

    public Bug() {
        this.likes = new ArrayList<>();
        this.likers = new ArrayList<>();
    }
}
