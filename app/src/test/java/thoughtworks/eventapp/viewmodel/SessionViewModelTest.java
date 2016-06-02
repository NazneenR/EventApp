package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import java.text.ParseException;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static thoughtworks.eventapp.DateUtil.getDate;

public class SessionViewModelTest {

  @Test
  public void shouldDisplayFormattedDurationWithMinutes() throws ParseException {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        getDate("2016-09-24T16:30:00+05:30"), getDate("2016-09-24T17:15:00+05:30"), Category.BELONG, "Ballroom");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("04:30 PM - 05:15 PM (45min)"));
  }

  @Test
  public void shouldDisplayFormattedDurationWithHours() throws ParseException {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        getDate("2016-09-24T21:00:00+05:30"), getDate("2016-09-24T23:00:00+05:30"), Category.ASPIRE, "Ballroom");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("09:00 PM - 11:00 PM (2h)"));
  }

  @Test
  public void shouldDisplayFormattedDurationWithHoursAndMins() throws ParseException {
    final Session session = new Session("Keynote", "Desc", "24-09-2016",
        getDate("2016-09-24T21:00:00+05:30"), getDate("2016-09-24T23:55:00+05:30"), Category.BELONG, "Ballroom");
    final SessionViewModel sessionViewModel = new SessionViewModel(session);

    assertThat(sessionViewModel.getDisplayTime(), is("09:00 PM - 11:55 PM (2h 55min)"));
  }

}