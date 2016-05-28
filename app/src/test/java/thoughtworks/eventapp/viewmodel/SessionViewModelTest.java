package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import java.text.ParseException;

import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.testdata.TestDataCreator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static thoughtworks.eventapp.model.Category.BELONG;

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

  @Test
  public void showName() throws ParseException {
    SessionViewModel sessionViewModel = createSessionViewModel("03:30:00+05:30", "04:15:00+05:30");
    assertThat(sessionViewModel.getName(), is("Craft"));
  }

  private void verifyFormattedSessionTime(String startTimeAsString, String endTimeAsString, String formattedSessionTime) throws ParseException {
    SessionViewModel sessionViewModel = createSessionViewModel(startTimeAsString, endTimeAsString);
    assertThat(sessionViewModel.formattedSessionTime(),is(formattedSessionTime));
  }

  private SessionViewModel createSessionViewModel(String startTimeAsString, String endTimeAsString) throws ParseException {
    Session session = TestDataCreator.sessionFrom("Craft", BELONG, startTimeAsString, endTimeAsString);
    return new SessionViewModel(session);
  }
}
