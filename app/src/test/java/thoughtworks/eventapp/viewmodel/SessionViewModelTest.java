package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import thoughtworks.eventapp.model.Session;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SessionViewModelTest {

  @Test
  public void shouldDisplayFormattedDurationWithMinutes() {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        "2016-09-24T16:30:00+05:30", "2016-09-24T17:15:00+05:30");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("11:00 AM - 11:45 AM (45min)"));
  }

  @Test
  public void shouldDisplayFormattedDurationWithHours() {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        "2016-09-24T21:00:00+05:30", "2016-09-24T23:00:00+05:30");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("03:30 PM - 05:30 PM (2h)"));
  }

  @Test
  public void shouldDisplayFormattedDurationWithHoursAndMins() {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        "2016-09-24T21:00:00+05:30", "2016-09-24T23:55:00+05:30");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("03:30 PM - 06:25 PM (2h 55min)"));
  }

}