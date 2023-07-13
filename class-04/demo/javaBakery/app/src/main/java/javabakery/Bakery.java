package javabakery;

import javabakery.bakedGoods.Bearclaw;
import javabakery.bakedGoods.Cornetto;
import javabakery.bakedGoods.Danish;

import java.util.ArrayList;

public class Bakery {
  public static final String OPEN_TIME = "8:00 AM";
  private ArrayList<Bearclaw> bearclawInventory;
  private ArrayList<Cornetto> cornettoInventory;
  private ArrayList<Danish> danishInventory;
  private boolean soldOut;

  public Bakery() {
  }

  public Bakery(ArrayList<Cornetto> cornettoInventory, ArrayList<Bearclaw> bearclawInventory, ArrayList<Danish> danishInventory, boolean soldOut) {
    this.cornettoInventory = cornettoInventory;
    this.bearclawInventory = bearclawInventory;
    this.danishInventory = danishInventory;
    this.soldOut = soldOut;
  }

  public void makeSale() {
    // update inventory
    // make money for however many of pasteries sold
    System.out.println("We made a sale, yay!");
  }

  public ArrayList<Cornetto> getCornettoInventory() {
    return cornettoInventory;
  }

  public void setCornettoInventory(ArrayList<Cornetto> cornettoInventory) {
    this.cornettoInventory = cornettoInventory;
  }

  public ArrayList<Bearclaw> getBearclawInventory() {
    return bearclawInventory;
  }

  public void setBearclawInventory(ArrayList<Bearclaw> bearclawInventory) {
    this.bearclawInventory = bearclawInventory;
  }

  public ArrayList<Danish> getDanishInventory() {
    return danishInventory;
  }

  public void setDanishInventory(ArrayList<Danish> danishInventory) {
    this.danishInventory = danishInventory;
  }

  public boolean isSoldOut() {
    return soldOut;
  }

  public void setSoldOut(boolean soldOut) {
    this.soldOut = soldOut;
  }

  @Override
  public String toString() {
    return "Bakery{" +
      "bearclawInventory=" + bearclawInventory +
      ", cornettoInventory=" + cornettoInventory +
      ", danishInventory=" + danishInventory +
      ", soldOut=" + soldOut +
      '}';
  }
}
