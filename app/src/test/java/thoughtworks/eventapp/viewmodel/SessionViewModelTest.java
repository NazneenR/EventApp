package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SessionViewModelTest {

  @Test
  public void showFormattedSessionTimesAlongWithDuration() throws ParseException {
    verifyFormattedSessionTime("04:30:00+05:30", "05:30:00+05:30", "04:30 - 05:30 (1h)");
  }

  @Test
  public void showFormattedSessionTimesWithHoursAndMinutes() throws ParseException {
    verifyFormattedSessionTime("14:30:00+05:30", "19:00:00+05:30", "14:30 - 19:00 (4h 30min)");
  }

  @Test
  public void showFormattedSessionTimesWithZeroHoursAndSomeMinutes() throws ParseException {
    verifyFormattedSessionTime("03:30:00+05:30", "04:15:00+05:30", "03:30 - 04:15 (45min)");
  }

  private void verifyFormattedSessionTime(String startTimeAsString, String endTimeAsString, String formattedSessionTime) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date startTime = simpleDateFormat.parse("2016-05-23T"+startTimeAsString);
    Date endTime = simpleDateFormat.parse("2016-05-23T"+endTimeAsString);
    Session session = new Session("Craft", "Description of crafts", "2016-05-23",
        startTime, endTime, Category.BELONG);
    SessionViewModel sessionViewModel = new SessionViewModel(session);
    assertThat(sessionViewModel.formattedSessionTime(),is(formattedSessionTime));
  }
}
