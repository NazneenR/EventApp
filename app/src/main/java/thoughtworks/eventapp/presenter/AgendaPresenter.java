package thoughtworks.eventapp.presenter;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.constant.Constants;
import thoughtworks.eventapp.model.Sessions;
import thoughtworks.eventapp.view.AgendaView;

public class AgendaPresenter {

  private final APIClient apiClient;
  private final AgendaView agendaView;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void renderSessions(){
    apiClient.get(Constants.api, new APIClientCallback<Sessions>() {
      @Override
      public void onSuccess(Sessions sessions) {
        agendaView.render(sessions);
      }

      @Override
      public Class<Sessions> getClassOfType() {
        return Sessions.class;
      }
    });
  }
}
