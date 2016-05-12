package thoughtworks.eventapp.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.constant.Constants;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.Sessions;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaPresenter {

  private final APIClient apiClient;
  private final AgendaView agendaView;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void fetchSessions(){
    apiClient.get(Constants.api, new APIClientCallback<Sessions>() {
      @Override
      public void onSuccess(Sessions sessions) {
        List<ArrayList<SessionViewModel>> sessionViewModels = new ArrayList<>();
        // TODO: remove the date hardcoding
        sessionViewModels.add(getSessionViewModelsByDate(sessions, "2016-05-23"));
        sessionViewModels.add(getSessionViewModelsByDate(sessions, "2016-05-24"));
        agendaView.render(sessionViewModels);
      }

      @Override
      public Class<Sessions> getClassOfType() {
        return Sessions.class;
      }
    });
  }

  @NonNull
  private ArrayList<SessionViewModel> getSessionViewModelsByDate(Sessions sessions, String date) {
    ArrayList<SessionViewModel> sessionViewModels = new ArrayList<>();
    final List<Session> filteredSessions = sessions.filterByDate(date);
    for (Session session : filteredSessions) {
      sessionViewModels.add(new SessionViewModel(session));
    }
    return sessionViewModels;
  }
}
