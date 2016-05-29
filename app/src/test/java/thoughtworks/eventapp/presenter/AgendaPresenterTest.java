package thoughtworks.eventapp.presenter;

import org.junit.Test;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AgendaPresenterTest {
  @Test
  public void shouldCallApiClient(){
    APIClient apiClient = mock(APIClient.class);
    AgendaPresenter agendaPresenter = new AgendaPresenter(apiClient);
    agendaPresenter.fetchEvents();
    verify(apiClient, times(1)).get(any(String.class), any(APIClientCallback.class));
  }
}
