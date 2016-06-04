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
import static thoughtworks.eventapp.DateUtil.getDate;

public class AgendaPresenterTest {

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

    sessionInTrackOne = new Session("Craft", "Try your hand at craft", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), Category.CREATE, "Ballroom");
    sessionInTrackTwo = new Session("Keynote", "By Roy Singham", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.ASPIRE, "Pre Function Area");
    mockAPIClient();
  }

  @Test
  public void shouldCallRenderWithViewModelAfterFetchingData(){
    agendaPresenter.fetchSessions();

    InOrder inOrder = inOrder(agendaViewMock);
    inOrder.verify(agendaViewMock).showProgressDialog();
    inOrder.verify(agendaViewMock).render(sessionViewModelArgumentCaptor.capture());
    inOrder.verify(agendaViewMock).dismissProgressDialog();

    final ConferenceViewModel conferenceViewModel = sessionViewModelArgumentCaptor.getValue();

    assertThat("Craft", is(conferenceViewModel.sessionsAt(0).get(0).getName()));
    assertThat("Keynote", is(conferenceViewModel.sessionsAt(1).get(0).getName()));
    assertThat(3, is(conferenceViewModel.size()));
    assertThat(1, is(conferenceViewModel.sessionsAt(0).size()));
    assertThat(1, is(conferenceViewModel.sessionsAt(1).size()));
  }

  private void mockAPIClient() {
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
    }).when(apiClientMock).get(eq("https://intense-fire-9666.firebaseio.com/"), any(APIClientCallback.class));
  }
}