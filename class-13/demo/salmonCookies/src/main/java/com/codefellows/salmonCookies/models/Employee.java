package com.codefellows.salmonCookies.models;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Integer payPerHour;

    @ManyToOne
    CookieStand stand;

    protected Employee() {}


    public Employee(String name, Integer payPerHour, CookieStand stand) {
        this.name = name;
        this.payPerHour = payPerHour;
        this.stand = stand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(Integer payPerHour) {
        this.payPerHour = payPerHour;
    }

    public CookieStand getStand() {
        return stand;
    }

    public void setStand(CookieStand stand) {
        this.stand = stand;
    }
}
