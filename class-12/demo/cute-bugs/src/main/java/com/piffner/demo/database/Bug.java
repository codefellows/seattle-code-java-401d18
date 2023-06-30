package com.piffner.demo.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bug {

    @Id
    @GeneratedValue
    public long id;

    public String name;
    public int numberOfLegs;
    public int cutenessScale;
    public String typeOfBug;
}
