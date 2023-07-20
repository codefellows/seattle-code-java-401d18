package swapi;

public class StarWarsPersonProperties {
  String height;
  String hair_color;
  String birth_year;
  String name;

  public StarWarsPersonProperties(String height, String hair_color, String birth_year, String name) {
    this.height = height;
    this.hair_color = hair_color;
    this.birth_year = birth_year;
    this.name = name;
  }

  @Override
  public String toString() {
    return "StarWarsPersonProperties{" +
      "height='" + height + '\'' +
      ", hair_color='" + hair_color + '\'' +
      ", birth_year='" + birth_year + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
