package thoughtworks.eventapp.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.apiclient.APIClientCallback;
import thoughtworks.eventapp.model.Conference;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static thoughtworks.eventapp.Constants.CONFERENCE_ENDPOINT;
import static thoughtworks.eventapp.testdata.TestDataCreator.defaultConference;

@RunWith(AndroidJUnit4.class)
public class AgendaActivityTest {
  @Rule
  public ActivityTestRule<AgendaActivity> mActivityRule = new ActivityTestRule<>(AgendaActivity.class);
  private APIClient mockApiClient;

  @Test
  public void validateViewPagerTitles(){
    mockApiClient = mock(APIClient.class);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback<Conference> callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onSuccess(defaultConference());
        return null;
      }
    }).when(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
    onView(withId(R.id.greeting)).check(matches(withText("Hello World!")));
  }
}
