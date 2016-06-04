package thoughtworks.eventapp.repository;

import java.util.List;

import thoughtworks.eventapp.model.SessionDAO;

public class SessionRepository {

  public List<SessionDAO> getSavedSessions() {
    return SessionDAO.listAll(SessionDAO.class);
  }

  public void saveSession(SessionDAO session) {
    session.save();
  }
}
