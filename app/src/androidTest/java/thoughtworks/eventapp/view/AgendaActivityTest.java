package thoughtworks.eventapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thoughtworks.eventapp.EventAppAndroidJUnitRunner;
import thoughtworks.eventapp.R;
import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.Sessions;
import thoughtworks.eventapp.rule.ActivityUnitTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

@RunWith(AndroidJUnit4.class)
public class AgendaActivityTest {
  protected APIClient apiClient;

  @Rule
  public ActivityUnitTestRule<AgendaActivity> activityTestRule = new
      ActivityUnitTestRule<AgendaActivity>(getActivityProvider(), AgendaActivity.class, false, false) {
      };

  @Before
  public void setup() throws Exception {
    apiClient = mock(APIClient.class);
  }

  @Test
  public void shouldSwipeTab() throws InterruptedException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];

        Sessions sessions = new Sessions();
        Session session1 = new Session("Craft", "Try your hand at craft", "2016-05-23", getDate("2016-05-23T19:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), "Aspire");
        Session session2 = new Session("Keynote", "By Roy Singham", "2016-05-24", getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), "Create");
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        sessions.setSessions(sessionList);

        callback.onSuccess(sessions);
        return null;
      }
    }).when(apiClient).get(eq("https://intense-fire-9666.firebaseio.com/"), any(APIClientCallback.class));

    activityTestRule.launchActivity(new Intent());

    onView(withId(R.id.viewpager))
        .perform(swipeLeft());
  }

  @NonNull
  private EventAppAndroidJUnitRunner.ActivityProvider<AgendaActivity> getActivityProvider() {
    return new EventAppAndroidJUnitRunner.ActivityProvider<AgendaActivity>() {
      @Override
      public AgendaActivity getActivity() {
        return new AgendaActivity() {
          @NonNull
          @Override
          protected APIClient getApiClient() {
            return apiClient;
          }

          @Override
          protected void onDestroy() {
            super.onDestroy();
            reset(apiClient);
          }
        };
      }

      @Override
      public Class<AgendaActivity> getActivityClass() {
        return AgendaActivity.class;
      }
    };
  }

  //TODO: This method is duplicated with DateUtil.getDate(). Also SimpleDateFormat behaves differently for Junit and Android Test.
  private Date getDate(String dateString) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    return simpleDateFormat.parse(dateString);
  }

}
