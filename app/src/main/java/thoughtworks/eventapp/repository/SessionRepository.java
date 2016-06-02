package thoughtworks.eventapp.repository;

import java.util.List;

import thoughtworks.eventapp.model.Session;

public class SessionRepository {

  public List<Session> getSavedSessions() {
    return Session.listAll(Session.class);
  }

  public void saveSession(Session session) {
    session.save();
  }
}
