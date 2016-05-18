package thoughtworks.eventapp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.util.Date;

public class Session {
  private String name;
  private String description;
  private String date;
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  private Date startTime;
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  private Date endTime;
  private Category category;

  public Session() {
  }

  public Session(String name, String description, String date, Date startTime, Date endTime, Category category) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getDate() {
    return date;
  }

  public Date getStartTime() {
    return startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public Category getCategory() {
    return category;
  }
}
