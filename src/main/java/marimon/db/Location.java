package marimon.db;

/**
 * Created by ignasi on 06/10/14.
 */
class Location {
  public final int id;
  public final String name;

  public Location(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Location{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
