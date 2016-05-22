package thoughtworks.eventapp.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import thoughtworks.eventapp.model.Session;

public class SessionViewModel {
  private final Session session;

  public SessionViewModel(Session session) {
    this.session = session;
  }

  public String formattedSessionTime() {
    StringBuilder builder = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date startTime = session.getStartTime();
    Date endTime = session.getEndTime();
    long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - startTime.getTime());
    long numberOfHours = durationInMinutes / 60;

    builder.append(dateFormat.format(startTime)).append(" - ").append(dateFormat.format(endTime))
            .append(" (").append(numberOfHours).append("h").append(")");
//    long numberOfMinutes = durationInMinutes % 60;
    return builder.toString();
  }
}
