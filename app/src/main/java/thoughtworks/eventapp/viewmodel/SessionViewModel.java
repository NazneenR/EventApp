package thoughtworks.eventapp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.SimpleDateFormat;
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
    long duration = session.getEndTime().getTime() - session.getStartTime().getTime();
    long hrs = TimeUnit.MILLISECONDS.toHours(duration);
    long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
    String hms;
    if (hrs == 0) {
      hms = String.format("%02dmin", minutes - TimeUnit.HOURS.toMinutes(hrs));
    } else {
      if ((minutes - TimeUnit.HOURS.toMinutes(hrs)) == 0) {
        hms = String.format("%1dh", hrs);
      } else {
        hms = String.format("%1dh %02dmin", hrs, minutes - TimeUnit.HOURS.toMinutes(hrs));
      }
    }

    return new SimpleDateFormat("hh:mm aaa").format(session.getStartTime()) + " - " + new SimpleDateFormat("hh:mm aaa").format(session.getEndTime())
        + " (" + hms + ")";
  }

  public String getName() {
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
