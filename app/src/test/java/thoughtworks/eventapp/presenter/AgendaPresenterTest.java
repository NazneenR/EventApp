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
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.Sessions;
import thoughtworks.eventapp.view.AgendaView;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

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
  private ArgumentCaptor<List<ArrayList<SessionViewModel>>> sessionViewModelArgumentCaptor;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    agendaViewMock = mock(AgendaView.class);
    apiClientMock = mock(APIClient.class);
    agendaPresenter = new AgendaPresenter(apiClientMock, agendaViewMock);
  }

  @Test //Maybe need better name
  public void shouldCreateSessionViewModelByDate(){
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];

        Sessions sessions = new Sessions();
        Session session1 = new Session("Craft", "Try your hand at craft", "2016-05-23", getDate("2016-05-23T19:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), Category.CREATE);
        Session session2 = new Session("Keynote", "By Roy Singham", "2016-05-24", getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.ASPIRE);
        Session session3 = new Session("Chalte Chalte", "Prize distribution", "2016-05-24", getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), Category.BELONG);
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        sessions.setSessions(sessionList);

        callback.onSuccess(sessions);
        return null;
      }
    }).when(apiClientMock).get(eq("https://intense-fire-9666.firebaseio.com/"), any(APIClientCallback.class));

    agendaPresenter.fetchSessions();

    InOrder inOrder = inOrder(agendaViewMock);
    inOrder.verify(agendaViewMock).showProgressDialog();
    inOrder.verify(agendaViewMock).render(sessionViewModelArgumentCaptor.capture());
    inOrder.verify(agendaViewMock).dismissProgressDialog();

    final List<ArrayList<SessionViewModel>> sessions = sessionViewModelArgumentCaptor.getValue();

    assertThat("Craft", is(sessions.get(0).get(0).getName()));
    assertThat("Keynote", is(sessions.get(1).get(0).getName()));
    assertThat(3, is(sessions.size()));
    assertThat(1, is(sessions.get(0).size()));
    assertThat(1, is(sessions.get(1).size()));
    assertThat(1, is(sessions.get(2).size()));
  }
}