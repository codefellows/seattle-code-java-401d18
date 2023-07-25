package com.java401d18.salmonCookiesD18.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Step 1: add @entity to class
@Entity
public class SalmonCookieStore {
  // Step 2: add @Id identifier property to class
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

  String name;
  Integer averageCookiesPerDay;

  // Step 3: Add protected default constructor
  protected SalmonCookieStore() {
  }

  public SalmonCookieStore(long id, String name, Integer averageCookiesPerDay) {
    this.id = id;
    this.name = name;
    this.averageCookiesPerDay = averageCookiesPerDay;
  }

  public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
    this.name = name;
    this.averageCookiesPerDay = averageCookiesPerDay;
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

  @Override
  public String toString() {
    return "SalmonCookieStore{" +
      "name='" + name + '\'' +
      ", averageCookiesPerDay=" + averageCookiesPerDay +
      '}';
  }
}
