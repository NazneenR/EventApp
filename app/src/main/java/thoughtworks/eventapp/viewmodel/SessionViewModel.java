package thoughtworks.eventapp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.concurrent.TimeUnit;

import thoughtworks.eventapp.model.Session;

public class SessionViewModel implements Parcelable {
  private Session session;

  public SessionViewModel(Session session) {
    this.session = session;
  }

  protected SessionViewModel(Parcel in) {
    this.session = in.readParcelable(getClass().getClassLoader());
  }

  public static final Creator<SessionViewModel> CREATOR = new Creator<SessionViewModel>() {
    @Override
    public SessionViewModel createFromParcel(Parcel in) {
      return new SessionViewModel(in);
    }

    @Override
    public SessionViewModel[] newArray(int size) {
      return new SessionViewModel[size];
    }
  };

  public String getDisplayTime() {
    DateTimeFormatter dateTimeParser = ISODateTimeFormat.dateTimeParser();
    DateTime startTime = dateTimeParser.parseDateTime(session.getStartTime());
    DateTime endTime = dateTimeParser.parseDateTime(session.getEndTime());
    int duration = duration(startTime, endTime);
    long hrs = TimeUnit.MILLISECONDS.toHours(duration);
    long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
    String hms;
    if (hrs == 0) {
      hms = String.format("%02dmin", minutes - TimeUnit.HOURS.toMinutes(hrs));
    }
    else
    {
      if ((minutes - TimeUnit.HOURS.toMinutes(hrs)) == 0) {
        hms = String.format("%1dh", hrs);
      }
      else {
        hms = String.format("%1dh %02dmin", hrs, minutes - TimeUnit.HOURS.toMinutes(hrs));
      }
    }

    return startTime.toString("hh:mm aaa") + " - " + endTime.toString("hh:mm aaa")
        + " (" + hms+")";
  }

  private int duration(DateTime startTime, DateTime endTime) {
    Interval interval = new Interval(startTime, endTime);
    int hours = interval.toDuration().toPeriod().getHours();
    int minutes = interval.toDuration().toPeriod().getMinutes();
    return ((hours * 60) + minutes) * 60 * 1000;
  }

  public String getName(){
    return session.getName();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeParcelable(session, 0);
  }
}
