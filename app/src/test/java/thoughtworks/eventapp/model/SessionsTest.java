package thoughtworks.eventapp.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SessionsTest {


  @Test
  public void filterSessionsByCategory() {
    Sessions sessions = new Sessions();
    Session session1 = new Session("Craft", "Try your hand at craft", "23-05-2016", null, null, Category.BELONG);
    Session session2 = new Session("Craft", "Try your hand at craft", "24-05-2016", null, null, Category.CREATE);
    List<Session> sessionList = new ArrayList<>();
    sessionList.add(session1);
    sessionList.add(session2);
    sessions.setSessions(sessionList);

    final List<Session> filteredSessions = sessions.filterByCategory(Category.CREATE);

    assertThat(filteredSessions.size(), is(1));
    assertThat(filteredSessions.get(0), is(session2));
  }
}