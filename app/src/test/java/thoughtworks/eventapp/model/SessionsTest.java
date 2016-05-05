package thoughtworks.eventapp.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SessionsTest {

  @Test
  public void filterSessionsByDate() {
    Sessions sessions = new Sessions();
    Session session1 = new Session("Craft", "Try your hand at craft", "23-05-2016");
    Session session2 = new Session("Craft", "Try your hand at craft", "24-05-2016");
    List<Session> sessionList = new ArrayList<>();
    sessionList.add(session1);
    sessionList.add(session2);
    sessions.setSessions(sessionList);

    final List<Session> filteredSessions = sessions.filterByDate("24-05-2016");

    assertThat(filteredSessions.size(), is(1));
    assertThat(filteredSessions.get(0), is(session2));
  }
}
