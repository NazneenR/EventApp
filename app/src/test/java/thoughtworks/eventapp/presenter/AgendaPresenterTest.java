package thoughtworks.eventapp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.apiclient.NetworkException;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.repository.SessionRepository;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static thoughtworks.eventapp.DateUtil.getDate;

public class AgendaPresenterTest {

  public static final String NETWORK_ERROR = "There is No Network";
  public static final String URL = "https://intense-fire-9666.firebaseio.com/";
  private APIClient apiClientMock;
  private AgendaView agendaViewMock;
  private AgendaPresenter agendaPresenter;
  @Captor
  private ArgumentCaptor<ConferenceViewModel> sessionViewModelArgumentCaptor;
  private SessionRepository sessionRepository;
  private Session sessionInTrackOne;
  private Session sessionInTrackTwo;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    agendaViewMock = mock(AgendaView.class);
    apiClientMock = mock(APIClient.class);
    sessionRepository = mock(SessionRepository.class);
    agendaPresenter = new AgendaPresenter(apiClientMock, agendaViewMock);

    sessionInTrackOne = new Session("Craft", "Try your hand at craft",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), Category.CREATE, "Ballroom");
    sessionInTrackTwo = new Session("Keynote", "By Roy Singham",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.ASPIRE, "Pre Function Area");
  }

  @Test
  public void shouldCallRenderWithViewModelAfterFetchingData() {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];
        Conference conference = new Conference();
        List<Session> sessions = new ArrayList<>();
        sessions.add(sessionInTrackOne);
        sessions.add(sessionInTrackTwo);
        conference.setSessions(sessions);

        callback.onSuccess(conference);
        return null;
      }
    }).when(apiClientMock).get(eq(URL), any(APIClientCallback.class));

    agendaPresenter.fetchSessions();

    InOrder inOrder = inOrder(agendaViewMock);
    inOrder.verify(agendaViewMock).showProgressDialog();
    inOrder.verify(agendaViewMock).render(sessionViewModelArgumentCaptor.capture());
    inOrder.verify(agendaViewMock).dismissProgressDialog();

    final ConferenceViewModel conferenceViewModel = sessionViewModelArgumentCaptor.getValue();

    assertThat("Craft", is(conferenceViewModel.getCategoryViewModelAt(0).getSessionViewModels().get(0).getName()));
    assertThat("Keynote", is(conferenceViewModel.getCategoryViewModelAt(1).getSessionViewModels().get(0).getName()));
    assertThat(3, is(conferenceViewModel.sizeOfCategoryViewModels()));
    assertThat(1, is(conferenceViewModel.getCategoryViewModelAt(0).getSessionViewModels().size()));
    assertThat(1, is(conferenceViewModel.getCategoryViewModelAt(1).getSessionViewModels().size()));
  }

  @Test
  public void shouldCallShowDialogOnFailureOfFetchingData() {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onFailure(new NetworkException(NETWORK_ERROR));
        return null;
      }
    }).when(apiClientMock).get(eq(URL), any(APIClientCallback.class));
    agendaPresenter.fetchSessions();

    verify(agendaViewMock).showDialog(eq(NETWORK_ERROR));
  }

}