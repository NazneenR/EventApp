package thoughtworks.eventapp.model;

import org.junit.Test;

import java.util.List;

import thoughtworks.eventapp.testdata.TestDataCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConferenceTest {

  @Test
  public void filterSessionsByCategory() {
    Conference conference = TestDataCreator.defaultConference();
    final List<Session> filteredSessions = conference.filterByCategory(Category.CREATE);
    assertThat(filteredSessions.size(), is(1));
    assertThat(filteredSessions.get(0), is(TestDataCreator.SESSION_2));
  }
}