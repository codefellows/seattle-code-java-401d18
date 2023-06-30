package com.piffner.demo.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Location {
    //id | name | maxOccupancy | terrainType | hasFoliage

    @Id
    @GeneratedValue
    public long id;

    // Locations can have many bugs (ahhhhh!)
    // "mappedBy" is the field in the Bug class
    // that is for this location.
    // (This is necessary if a bug could have a homeLocation or workLocation,
    // for example.)
    @OneToMany(mappedBy = "location")
    List<Bug> bugs;

    public String name;
    public int maxOccupancy;
    public String terrainType;
    public boolean hasFoliage;

}
