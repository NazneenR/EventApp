package thoughtworks.eventapp.model;

public class Session {
  private String name;
  private String description;
  private String date;
  private String startTime;
  private String endTime;

  public Session() {
  }

  public String getStartTime() {
    return startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public Session(String name, String description, String date, String startTime, String endTime) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;

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
