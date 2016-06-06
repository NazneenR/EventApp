package thoughtworks.eventapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.EventAppAndroidJUnitRunner;
import thoughtworks.eventapp.R;
import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.apiclient.NetworkException;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.rule.ActivityUnitTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static thoughtworks.eventapp.model.Category.ASPIRE;
import static thoughtworks.eventapp.model.Category.BELONG;
import static thoughtworks.eventapp.model.Category.CREATE;
import static thoughtworks.eventapp.utils.CustomEspressoMatcher.atPositionInViewGroup;
import static thoughtworks.eventapp.utils.CustomEspressoMatcher.withNumberOfChildren;
import static thoughtworks.eventapp.utils.DateUtil.getDate;

@RunWith(AndroidJUnit4.class)
public class AgendaActivityTest {
  public static final String URL = "https://intense-fire-9666.firebaseio.com/";
  public static final String THERE_IS_NO_NETWORK = "There is no network";
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
  public void shouldShowCategoriesAsViewPagersTitle(){
    mockAPIClientForSuccess();

    activityTestRule.launchActivity(new Intent());

    Matcher<View> slidingTabStripViewMatcher = withClassName(is("android.support.design.widget.TabLayout$SlidingTabStrip"));
    onView(slidingTabStripViewMatcher).check(matches(withNumberOfChildren(3)));
    onView(atPositionInViewGroup(slidingTabStripViewMatcher, 0)).check(matches(withChild(withText("CREATE"))));
    onView(atPositionInViewGroup(slidingTabStripViewMatcher, 1)).check(matches(withChild(withText("ASPIRE"))));
    onView(atPositionInViewGroup(slidingTabStripViewMatcher, 2)).check(matches(withChild(withText("BELONG"))));

  }

  @Test
  public void shouldSwipeTab() throws InterruptedException {
    mockAPIClientForSuccess();

    activityTestRule.launchActivity(new Intent());

    onView(withId(R.id.viewpager)).perform(swipeLeft());
  }

  @Test
  public void shouldShowDialogOnFailureOfFetchingData(){
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onFailure(new NetworkException(THERE_IS_NO_NETWORK));
        return null;
      }
    }).when(apiClient).get(eq(URL), any(APIClientCallback.class));

    activityTestRule.launchActivity(new Intent());

    onView(withId(android.R.id.message)).inRoot(isDialog()).check(matches(withText(THERE_IS_NO_NETWORK)));
    onView(withId(android.R.id.button1)).inRoot(isDialog()).check(matches(withText("OK")));
    onView(withId(android.R.id.button1)).inRoot(isDialog()).perform(click());
    assertTrue(activityTestRule.getActivity().isFinishing());
  }

  private void mockAPIClientForSuccess() {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback callback = (APIClientCallback) invocation.getArguments()[1];

        Conference conference = new Conference();
        Session session1 = new Session("Craft", "Try your hand at craft", getDate("2016-05-23T19:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), ASPIRE, "Ballroom");
        Session session2 = new Session("Craft", "Try your hand at craft", getDate("2016-05-23T19:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), BELONG, "Ballroom");
        Session session3 = new Session("Keynote", "By Roy Singham", getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T18:15:00+05:30"), CREATE, "Pre Function Area");
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        conference.setSessions(sessionList);

        callback.onSuccess(conference);
        return null;
      }
    }).when(apiClient).get(eq(URL), any(APIClientCallback.class));
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
}
