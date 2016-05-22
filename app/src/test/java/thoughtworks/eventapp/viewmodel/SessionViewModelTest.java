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
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date startTime = simpleDateFormat.parse("2016-05-23T04:30:00+05:30");
    Date endTime = simpleDateFormat.parse("2016-05-23T05:30:00+05:30");
    Session session = new Session("Craft", "Description of crafts", "2016-05-23",
        startTime, endTime, Category.BELONG);
    SessionViewModel sessionViewModel = new SessionViewModel(session);
    assertThat(sessionViewModel.formattedSessionTime(),is("04:30 - 05:30 (1h)"));
  }
}
