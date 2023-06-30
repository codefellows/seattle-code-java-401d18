package com.codefellows.salmonCookies2.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

// To store this in the db:
// 1. Add the @Entity annotation
@Entity
public class SalmonCookiesStore
{
    // 2. Add @Id and @GeneratedValue annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    //If you need longer than 255 characters, use these two annotations
    // @Lob
    // @Type(type = "org.hibernate.type.TextType")
    String name;
    int averageCookiesPerDay;
    @OneToMany(mappedBy = "myStore")
    // PS: If we want store deletions to delete all the employees, then use something like
    //     @OneToMany(mappedBy="myStore", cascade = CascadeType.ALL)
    List<Employee> employeesAtThisStore;

    // 3. Remake the default constructor (if you need it)
    protected SalmonCookiesStore()
    {

    }

    public SalmonCookiesStore(String name, int averageCookiesPerDay)
    {
        this.name = name;
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAverageCookiesPerDay()
    {
        return averageCookiesPerDay;
    }

    public void setAverageCookiesPerDay(int averageCookiesPerDay)
    {
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    public List<Employee> getEmployeesAtThisStore()
    {
        return employeesAtThisStore;
    }

    public void setEmployeesAtThisStore(List<Employee> employeesAtThisStore)
    {
        this.employeesAtThisStore = employeesAtThisStore;
    }
}
