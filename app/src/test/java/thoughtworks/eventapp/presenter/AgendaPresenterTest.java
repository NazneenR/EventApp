package thoughtworks.eventapp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.inOrder;
import static thoughtworks.eventapp.Constants.CONFERENCE_ENDPOINT;
import static thoughtworks.eventapp.testdata.TestDataCreator.defaultConference;

public class AgendaPresenterTest {

  private APIClient mockApiClient;
  private AgendaView mockAgendaView;
  private AgendaPresenter agendaPresenter;
  @Captor
  private ArgumentCaptor<ConferenceViewModel> conferenceVMCaptor;


  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    mockApiClient = mock(APIClient.class);
    mockAgendaView = mock(AgendaView.class);
    agendaPresenter = new AgendaPresenter(mockApiClient, mockAgendaView);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback<Conference> callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onSuccess(defaultConference());
        return null;
      }
    }).when(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
  }

  @Test
  public void shouldCallApiClient(){
    agendaPresenter.fetchEvents();
    verify(mockApiClient, times(1)).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
  }

  @Test
  public void shouldRenderViewOnApiResponse(){
    agendaPresenter.fetchEvents();
    verify(mockAgendaView, times(1)).render(conferenceVMCaptor.capture());
    ConferenceViewModel conferenceViewModel = conferenceVMCaptor.getValue();
    assertThat(conferenceViewModel.categoryViewModels().size(), is(3));
    assertThat(conferenceViewModel.categoryViewModels().get(2).getCategoryNameInTitleCase(),
                is("Create"));
  }

  @Test
  public void showProgressDialogRenderResponseAndHideDialogInOrder(){
    agendaPresenter.fetchEvents();
    InOrder inOrder = inOrder(mockAgendaView, mockApiClient);
    inOrder.verify(mockAgendaView).showProgressDialog();
    inOrder.verify(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
    inOrder.verify(mockAgendaView).render(conferenceVMCaptor.capture());
    inOrder.verify(mockAgendaView).dismissProgressDialog();

  }
}
