package thoughtworks.eventapp.presenter;

import org.joda.time.Interval;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.repository.SessionRepository;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaPresenter {

  public final static String api = "https://intense-fire-9666.firebaseio.com/";
  private final APIClient apiClient;
  private final AgendaView agendaView;
  private final SessionRepository sessionRepository;
  private Conference conference;
  private ConferenceViewModel conferenceViewModel;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView, SessionRepository sessionRepository) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
    this.sessionRepository = sessionRepository;
  }

  public void fetchSessions(){
    agendaView.showProgressDialog();
    apiClient.get(api, new APIClientCallback<Conference>() {

      @Override
      public void onSuccess(Conference conference) {
        AgendaPresenter.this.conference = conference;
        conferenceViewModel = ConferenceViewModel.createFrom(Arrays.asList(Category.values()), conference);
        agendaView.render(conferenceViewModel);
        agendaView.dismissProgressDialog();
      }

      @Override
      public Class<Conference> getClassOfType() {
        return Conference.class;
      }
    });
  }

  public void addSession(SessionViewModel sessionViewModel, Category category){
    Session sessionToAdd = mapToSession(sessionViewModel, category);
    addSession(sessionToAdd);
  }

  private void addSession(Session sessionToAdd) {
    List<Session> allSavedSessions = sessionRepository.getSavedSessions();
    final Date startTimeOfNewSession = sessionToAdd.getStartTime();
    final Date endTimeOfNewSession = sessionToAdd.getEndTime();
    for (Session session : allSavedSessions) {
      Interval interval1 = new Interval(startTimeOfNewSession.getTime(), endTimeOfNewSession.getTime());
      Interval interval2 = new Interval(session.getStartTime().getTime(), session.getEndTime().getTime());
      if(interval1.overlap(interval2) != null){
        agendaView.showConflictPopup();
      } else {
        agendaView.showSessionAddedSuccessfully();
        sessionRepository.saveSession(sessionToAdd);
      }
    }
  }

  private Session mapToSession(SessionViewModel sessionViewModel, Category category) {
    int categoryIndex = conferenceViewModel.indexOf(category);
    List<SessionViewModel> sessionViewModels = conferenceViewModel.sessionsAt(categoryIndex);
    int indexOfSessionViewModel = sessionViewModels.indexOf(sessionViewModel);
    return conference.filterByCategory(category).get(indexOfSessionViewModel);
  }

}