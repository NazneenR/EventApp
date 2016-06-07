package thoughtworks.eventapp.model;

import java.util.ArrayList;
import java.util.List;

public class Conference {
  private List<Session> sessions;

  public Conference(List<Session> sessions){
    this.sessions = sessions;
  }

  public List<Session> filterByCategory(Category category) {
    List<Session> filteredSessions = new ArrayList<>();
    for (Session session : sessions) {
      if (category.equals(session.getCategory())) filteredSessions.add(session);
    }
    return filteredSessions;
  }
}
