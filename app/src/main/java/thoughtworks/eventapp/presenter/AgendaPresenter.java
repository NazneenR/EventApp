package thoughtworks.eventapp.presenter;

import java.util.Arrays;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

public class AgendaPresenter {

  public final static String api = "https://intense-fire-9666.firebaseio.com/";
  private final APIClient apiClient;
  private final AgendaView agendaView;
  private ConferenceViewModel conferenceViewModel;

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void fetchSessions(){
    agendaView.showProgressDialog();
    apiClient.get(api, new APIClientCallback<Conference>() {

      @Override
      public void onSuccess(Conference conference) {
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
}