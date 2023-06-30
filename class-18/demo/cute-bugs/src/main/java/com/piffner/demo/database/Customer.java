package com.piffner.demo.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    public long id;

    public String name;

    @ManyToOne
    public HairDresser hairDresser;
}
