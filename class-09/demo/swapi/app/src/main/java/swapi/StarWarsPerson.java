package swapi;

public class StarWarsPerson {
  StarWarsPersonResult result;
  String message;

  public StarWarsPerson(StarWarsPersonResult result, String message) {
    this.result = result;
    this.message = message;
  }

  @Override
  public String toString() {
    return "StarWarsPerson{" +
      "result=" + result +
      ", message='" + message + '\'' +
      '}';
  }
}
