package javastreams;

public class Java401Person implements Comparable<Java401Person> {
  String name;
  int numOfPets;
  String hobby;
  boolean isTired;
  boolean isSmart;

  public Java401Person(String name, int numOfPets, String hobby, boolean isTired, boolean isSmart) {
    this.name = name;
    this.numOfPets = numOfPets;
    this.hobby = hobby;
    this.isTired = isTired;
    this.isSmart = isSmart;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }

  public int getNumOfPets() {
    return numOfPets;
  }

  public String getHobby() {
    return hobby;
  }

  public boolean isTired() {
    return isTired;
  }

  public boolean isSmart() {
    return isSmart;
  }

  @Override
  public int compareTo(Java401Person o) {
    return this.name.compareTo(o.name);
  }
}
