package thoughtworks.eventapp.presenter;

import org.junit.Test;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static thoughtworks.eventapp.Constants.CONFERENCE_ENDPOINT;

public class AgendaPresenterTest {
  @Test
  public void shouldCallApiClient(){
    APIClient apiClient = mock(APIClient.class);
    AgendaPresenter agendaPresenter = new AgendaPresenter(apiClient);
    agendaPresenter.fetchEvents();
    verify(apiClient, times(1)).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
  }
}
