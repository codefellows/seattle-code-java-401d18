package com.piffner.demo.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    public String name;
    public int numberOfLegs;
    public int cutenessScale;
    public String typeOfBug;
}
