package thoughtworks.eventapp.presenter;

import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.SessionDAO;
import thoughtworks.eventapp.repository.SessionRepository;
import thoughtworks.eventapp.view.DetailView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static thoughtworks.eventapp.DateUtil.getDate;

public class SessionDetailsPresenterTest {

  private SessionDetailsPresenter detailsPresenter;
  private SessionDAO sessionDAOOne;
  private SessionRepository sessionRepository;
  private DetailView detailViewMock;
  private Session sessionInTrackOne;
  private SessionDAO sessionDAOTwo;
  private Session sessionInTrackTwo;

  @Before
  public void setup() throws ParseException {
    sessionRepository = mock(SessionRepository.class);
    detailViewMock = mock(DetailView.class);
    final Resources resources = mock(Resources.class);
    detailsPresenter = new SessionDetailsPresenter(detailViewMock, sessionRepository, resources);

    when(resources.getString(R.string.session_saved)).thenReturn("Session successfully saved");

    sessionInTrackOne = new Session("Craft", "Try your hand at craft", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), Category.CREATE, "Ballroom");
    sessionInTrackTwo = new Session("Keynote", "Try your hand at craft", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.CREATE, "Pre function area");

    sessionDAOOne = SessionDAO.createFrom(sessionInTrackOne);
    sessionDAOTwo = SessionDAO.createFrom(sessionInTrackTwo);
  }

  @Test
  public void showConflictPopupIfAParallelSessionIsSaved() throws ParseException {
    List<SessionDAO> savedSessions = new ArrayList<>();
    savedSessions.add(sessionDAOTwo);

    when(sessionRepository.getSavedSessions()).thenReturn(savedSessions);

    detailsPresenter.addSession(sessionInTrackOne);

    verify(detailViewMock).showConflictPopup("Keynote","Craft");
  }

  @Test
  public void showConflictPopupIfASessionStartingDuringAOnGoingParallelSessionIsSaved() throws ParseException {
    List<SessionDAO> savedSessions = new ArrayList<>();
    savedSessions.add(sessionDAOOne);

    when(sessionRepository.getSavedSessions()).thenReturn(savedSessions);

    detailsPresenter.addSession(sessionInTrackTwo);

    verify(detailViewMock).showConflictPopup("Craft", "Keynote");
  }

  @Test
  public void showSaveSessionIfNoConflictingSessionFound() throws ParseException {
    sessionInTrackTwo = new Session("Keynote", "By Roy Singham", "2016-05-24",
        getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.ASPIRE, "Ballroom");

    SessionDAO expectedSessionDAO = SessionDAO.createFrom(sessionInTrackTwo);

    List<SessionDAO> savedSessions = new ArrayList<>();
    savedSessions.add(sessionDAOOne);

    when(sessionRepository.getSavedSessions()).thenReturn(savedSessions);

    detailsPresenter.addSession(sessionInTrackTwo);

    verify(detailViewMock).showSessionAddedSuccessfully("Session successfully saved");
    verify(sessionRepository).saveSession(expectedSessionDAO);
  }
}