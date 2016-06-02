package thoughtworks.eventapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

public class Session extends SugarRecord<Session> implements Parcelable {
  private String name;
  private String description;
  private String date;
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  private Date startTime;
  @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
  private Date endTime;
  private Category category;
  private String location;

  public Session() {
  }

  protected Session(Parcel in) {
    name = in.readString();
    location = in.readString();
    description = in.readString();
    date = in.readString();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(in.readLong());
    startTime = calendar.getTime();
    calendar.setTimeInMillis(in.readLong());
    endTime = calendar.getTime();
    category = Enum.valueOf(Category.class, in.readString());
  }

  public static final Creator<Session> CREATOR = new Creator<Session>() {
    @Override
    public Session createFromParcel(Parcel in) {
      return new Session(in);
    }

    @Override
    public Session[] newArray(int size) {
      return new Session[size];
    }
  };

  public Date getStartTime() {
    return startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public String getLocation() {
    return location;
  }

  public Session(String name, String description, String date, Date startTime, Date endTime, Category category, String location) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.category = category;
    this.location = location;
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

  public Category getCategory() {
    return category;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeString(description);
    parcel.writeString(date);
    parcel.writeString(location);
    parcel.writeLong(startTime.getTime());
    parcel.writeLong(endTime.getTime());
    parcel.writeString(category.name());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Session session = (Session) o;

    if (name != null ? !name.equals(session.name) : session.name != null) return false;
    if (location != null ? !location.equals(session.location) : session.location != null) return false;
    if (description != null ? !description.equals(session.description) : session.description != null)
      return false;
    if (date != null ? !date.equals(session.date) : session.date != null) return false;
    if (startTime != null ? !startTime.equals(session.startTime) : session.startTime != null)
      return false;
    if (endTime != null ? !endTime.equals(session.endTime) : session.endTime != null) return false;
    return category == session.category;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (location != null ? location.hashCode() : 0);
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
    result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
    result = 31 * result + (category != null ? category.hashCode() : 0);
    return result;
  }
}