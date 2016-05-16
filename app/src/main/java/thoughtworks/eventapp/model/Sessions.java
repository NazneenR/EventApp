package thoughtworks.eventapp.model;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
  private List<Session> sessions;

  public void setSessions(List<Session> sessions) {
    this.sessions = sessions;
  }

  //TODO: I do not like exposing this
  public List<Session> getSessions() {
    return sessions;
  }

  public List<Session> filterByCategory(Category category) {
    List<Session> filteredSessions = new ArrayList<>();
    for (Session session : sessions) {
      if (category.equals(session.getCategory())) filteredSessions.add(session);
    }
    return filteredSessions;
  }
}
