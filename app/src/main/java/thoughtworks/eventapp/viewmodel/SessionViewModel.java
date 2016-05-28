package thoughtworks.eventapp.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import thoughtworks.eventapp.model.Session;

public class SessionViewModel {
  private final Session session;

  public SessionViewModel(Session session) {
    this.session = session;
  }

  public String formattedSessionTime() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date startTime = session.getStartTime();
    Date endTime = session.getEndTime();
    long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - startTime.getTime());
    String formattedDuration = formattedDuration(durationInMinutes);
    return String.format("%s - %s (%s)", dateFormat.format(startTime), dateFormat.format(endTime),
                          formattedDuration);
  }

  private String formattedDuration(long durationInMinutes){
    long numberOfHours = durationInMinutes / 60;
    long numberOfMinutes = durationInMinutes % 60;
    String formattedHours = numberOfHours==0?"":numberOfHours+"h ";
    String formattedMinutes = numberOfMinutes==0?"":numberOfMinutes+"min";
    return (formattedHours + formattedMinutes).trim();
  }

  public String getName() {
    return session.getName();
  }
}
