package thoughtworks.eventapp.repository;

import java.util.List;

import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class SessionRepository {
  public List<Session> getSavedSessions() {
    return Session.listAll(Session.class);
  }

  public void saveSession(SessionViewModel sessionViewModel) {
    sessionViewModel.addtoDB();
  }
}
