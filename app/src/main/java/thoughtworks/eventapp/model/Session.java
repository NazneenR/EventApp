package thoughtworks.eventapp.model;

public class Session {
  private String name;
  private String description;
  private String date;

  public Session() {
  }

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
