package thoughtworks.eventapp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aaa");
    Date startTime = session.getStartTime();
    Date endTime = session.getEndTime();
    long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - startTime.getTime());
    String formattedDuration = formattedDuration(durationInMinutes);
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

  private String formattedDuration(long durationInMinutes){
    long numberOfHours = durationInMinutes / 60;
    long numberOfMinutes = durationInMinutes % 60;
    String formattedHours = numberOfHours==0?"":numberOfHours+"h ";
    String formattedMinutes = numberOfMinutes==0?"":numberOfMinutes+"min";
    return (formattedHours + formattedMinutes).trim();
  }
}
