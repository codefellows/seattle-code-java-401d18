package com.codefellows.salmonCookies.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CookieStand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    Integer averageCookiesPerDay;



    @OneToMany(mappedBy = "stand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Employee> employees;




    public CookieStand(String name, Integer averageCookiesPerDay) {
        this.name = name;
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    protected CookieStand() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAverageCookiesPerDay() {
        return averageCookiesPerDay;
    }

    public void setAverageCookiesPerDay(Integer averageCookiesPerDay) {
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "CookieStand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageCookiesPerDay=" + averageCookiesPerDay +
                ", employees=" + employees +
                '}';
    }
}
