package swapi;

public class StarWarsPersonResult {
  StarWarsPersonProperties properties;
  String description;

  public StarWarsPersonResult(StarWarsPersonProperties properties, String description) {
    this.properties = properties;
    this.description = description;
  }

  @Override
  public String toString() {
    return "StarWarsPersonResult{" +
      "properties=" + properties +
      ", description='" + description + '\'' +
      '}';
  }
}
