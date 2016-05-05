package thoughtworks.eventapp.model;

public class Session {
  private final String name;
  private final String description;
  private final String date;

  public Session(String name, String description, String date) {
    this.name = name;
    this.description = description;
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }
}
