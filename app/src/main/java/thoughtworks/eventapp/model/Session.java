package thoughtworks.eventapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Session implements Parcelable {
  private String name;
  private String description;
  private String date;
  private String startTime;
  private String endTime;

  public Session() {
  }

  protected Session(Parcel in) {
    name = in.readString();
    description = in.readString();
    date = in.readString();
    startTime = in.readString();
    endTime = in.readString();
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(name);
    parcel.writeString(description);
    parcel.writeString(date);
    parcel.writeString(startTime);
    parcel.writeString(endTime);
  }
}
