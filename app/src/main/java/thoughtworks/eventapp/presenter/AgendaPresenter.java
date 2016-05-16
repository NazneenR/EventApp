package thoughtworks.eventapp.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.Sessions;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaPresenter {

  public final static String api = "https://intense-fire-9666.firebaseio.com/";
  private final APIClient apiClient;
  private final AgendaView agendaView;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void fetchSessions(){
    apiClient.get(api, new APIClientCallback<Sessions>() {
      @Override
      public void onSuccess(Sessions sessions) {
        List<ArrayList<SessionViewModel>> sessionViewModels = new ArrayList<>();
        // TODO: remove the date hardcoding
        sessionViewModels.add(getSessionViewModelsByCategory(sessions, "Create"));
        sessionViewModels.add(getSessionViewModelsByCategory(sessions, "Aspire"));
        sessionViewModels.add(getSessionViewModelsByCategory(sessions, "Belong"));
        agendaView.render(sessionViewModels);
      }

      @Override
      public Class<Sessions> getClassOfType() {
        return Sessions.class;
      }
    });
  }

  @NonNull
  private ArrayList<SessionViewModel> getSessionViewModelsByCategory(Sessions sessions, String category) {
    ArrayList<SessionViewModel> sessionViewModels = new ArrayList<>();
    final List<Session> filteredSessions = sessions.filterByCategory(category);
    for (Session session : filteredSessions) {
      sessionViewModels.add(new SessionViewModel(session));
    }
    return sessionViewModels;
  }
}
