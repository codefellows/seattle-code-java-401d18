package com.codefellows.salmonCookies2.models;

import javax.persistence.*;

@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    protected Employee()
    {

    }

    public Employee(String name, SalmonCookiesStore store)
    {
        this.name = name;
        myStore = store;
    }

    String name;
    @ManyToOne
    SalmonCookiesStore myStore;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
