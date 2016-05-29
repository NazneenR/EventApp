package thoughtworks.eventapp.presenter;

import thoughtworks.eventapp.apiclient.APIClient;

import static thoughtworks.eventapp.Constants.CONFERENCE_ENDPOINT;

public class AgendaPresenter {
  private final APIClient apiClient;

  public AgendaPresenter(APIClient apiClient) {
    this.apiClient = apiClient;
  }

  public void fetchEvents() {
    apiClient.get(CONFERENCE_ENDPOINT, null);
  }
}
