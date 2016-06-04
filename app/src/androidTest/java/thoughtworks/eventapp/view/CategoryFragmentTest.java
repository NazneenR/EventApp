package thoughtworks.eventapp.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import thoughtworks.eventapp.testactivity.TestFragmentContainerActivity;
import thoughtworks.eventapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CategoryFragmentTest {
  @Rule
  public ActivityTestRule<TestFragmentContainerActivity> mActivityRule = new ActivityTestRule<>(TestFragmentContainerActivity.class);

  @Test
  public void renderSessionTimings(){
    onView(withId(R.id.test_fragment_container)).check(matches(isDisplayed()));
  }
}
