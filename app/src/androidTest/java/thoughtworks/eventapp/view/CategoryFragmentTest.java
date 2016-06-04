package thoughtworks.eventapp.view;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.rules.FragmentTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CategoryFragmentTest {
  @Rule
  public FragmentTestRule fragmentTestRule = new FragmentTestRule();

  @Test
  public void renderSessionTimings(){
    CategoryFragment fragment = new CategoryFragment();
    fragmentTestRule.launch(fragment);
    onView(withId(R.id.test_fragment_container)).check(matches(isDisplayed()));
  }
}
