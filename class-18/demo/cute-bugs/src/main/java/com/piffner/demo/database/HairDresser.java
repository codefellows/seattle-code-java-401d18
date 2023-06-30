package com.piffner.demo.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HairDresser {
    @Id
    @GeneratedValue
    public long id;

    public String name;

    @OneToMany(mappedBy = "hairDresser")
    public List<Customer> customers;

    public HairDresser() {
        // This will be null unless it was pulled from a repo
        // so if you want to do something with the array
        // after doing a new HairDresser();
        // you have to initialize it first.
        customers = new ArrayList<>();
    }
}
