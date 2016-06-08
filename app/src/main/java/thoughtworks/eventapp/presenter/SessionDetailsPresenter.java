package thoughtworks.eventapp.presenter;

import android.content.res.Resources;

import org.joda.time.Interval;
import java.util.Date;
import java.util.List;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.SessionDAO;
import thoughtworks.eventapp.repository.SessionRepository;
import thoughtworks.eventapp.view.DetailView;

public class SessionDetailsPresenter {

  private final DetailView detailView;
  private final SessionRepository sessionRepository;
  private final Resources resources;

  public SessionDetailsPresenter(DetailView detailView, SessionRepository sessionRepository, Resources resources){
    this.detailView = detailView;
    this.sessionRepository = sessionRepository;
    this.resources = resources;
  }

  public void addSession(Session sessionToAdd){
    List<SessionDAO> allSavedSessions = sessionRepository.getSavedSessions();
    final Date startTimeOfNewSession = sessionToAdd.getStartTime();
    final Date endTimeOfNewSession = sessionToAdd.getEndTime();
    if(allSavedSessions.isEmpty())
      saveSession(sessionToAdd);
    else {
      for (SessionDAO session : allSavedSessions) {
        Interval interval1 = new Interval(startTimeOfNewSession.getTime(), endTimeOfNewSession.getTime());
        Interval interval2 = new Interval(session.getStartTime().getTime(), session.getEndTime().getTime());
        if (interval1.overlap(interval2) != null) {
          detailView.showConflictPopup(session.getName(), sessionToAdd.getName());
        } else {
          saveSession(sessionToAdd);
        }
      }
    }
  }

  private void saveSession(Session sessionToAdd) {
    sessionRepository.saveSession(SessionDAO.createFrom(sessionToAdd));
    detailView.showSessionAddedSuccessfully(resources.getString(R.string.session_saved));
  }
}