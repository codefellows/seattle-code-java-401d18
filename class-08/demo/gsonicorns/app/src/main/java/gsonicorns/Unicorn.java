package gsonicorns;

import java.util.ArrayList;

public class Unicorn {
  // ***PROPERTIES***
  ArrayList<String> colors;
  boolean isMajestic;
  Integer hooves;
  Integer hornLength;

  // ***CONSTRUCTOR***
  public Unicorn(ArrayList<String> colors, boolean isMajestic, Integer hooves, Integer hornLength) {
    this.colors = colors;
    this.isMajestic = isMajestic;
    this.hooves = hooves;
    this.hornLength = hornLength;
  }

  // ***GETTERS AND SETTERS***
  public ArrayList<String> getColors() {
    return colors;
  }

  public void setColors(ArrayList<String> colors) {
    this.colors = colors;
  }

  public boolean isMajestic() {
    return isMajestic;
  }

  public void setMajestic(boolean majestic) {
    isMajestic = majestic;
  }

  public Integer getHooves() {
    return hooves;
  }

  public void setHooves(Integer hooves) {
    this.hooves = hooves;
  }

  public Integer getHornLength() {
    return hornLength;
  }

  public void setHornLength(Integer hornLength) {
    this.hornLength = hornLength;
  }

  // ***METHODS***
  public void fly() {
    System.out.println("I'm flying!");
  }

  public void laserBeam() {
    System.out.println("Pew pew");
  }

  @Override
  public String toString() {
    return "Unicorn{" +
      "colors=" + colors +
      ", isMajestic=" + isMajestic +
      ", hooves=" + hooves +
      ", hornLength=" + hornLength +
      '}';
  }
}
