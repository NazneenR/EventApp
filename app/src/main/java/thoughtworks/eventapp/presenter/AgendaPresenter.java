package thoughtworks.eventapp.presenter;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

import static thoughtworks.eventapp.Constants.CONFERENCE_ENDPOINT;

public class AgendaPresenter {
  private final APIClient apiClient;
  private final AgendaView agendaView;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void fetchEvents() {
    apiClient.get(CONFERENCE_ENDPOINT, new APIClientCallback<Conference>() {
      @Override
      public void onSuccess(Conference conference) {
        agendaView.render(new ConferenceViewModel(conference));
      }

      @Override
      public Class<Conference> getClassOfType() {
        return null;
      }
    });
  }
}
