package thoughtworks.eventapp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import thoughtworks.eventapp.model.Session;

public class SessionViewModel implements Parcelable {
  private Session session;

  private SessionViewModel(Session session) {
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
    Date startTime = session.getStartTime();
    Date endTime = session.getEndTime();
    String formattedDuration = getDisplayDuration();

    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
    return String.format("%s - %s (%s)", dateFormat.format(startTime), dateFormat.format(endTime),
        formattedDuration);
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

  private String getDisplayDuration(){
    long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(session.getEndTime().getTime() - session.getStartTime().getTime());
    long numberOfHours = durationInMinutes / 60;
    long numberOfMinutes = durationInMinutes % 60;
    String formattedHours = numberOfHours==0?"":numberOfHours+"h ";
    String formattedMinutes = numberOfMinutes==0?"":numberOfMinutes+"min";
    return (formattedHours + formattedMinutes).trim();
  }

  public Date getStartTime() {
   return session.getStartTime();
  }

  public Date getEndTime() {
    return session.getEndTime();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SessionViewModel that = (SessionViewModel) o;

    return session != null ? session.equals(that.session) : that.session == null;
  }

  @Override
  public int hashCode() {
    return session != null ? session.hashCode() : 0;
  }

  public String getDescription() {
    return session.getDescription();
  }

  public String getLocation() {
    return session.getLocation();
  }

  public static SessionViewModel createFrom(Session session) {
    return new SessionViewModel(session);
  }
}
