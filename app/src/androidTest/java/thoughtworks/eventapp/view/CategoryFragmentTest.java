package thoughtworks.eventapp.view;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.rules.FragmentTestRule;
import thoughtworks.eventapp.viewmodel.CategoryViewModel;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static thoughtworks.eventapp.testdata.TestDataCreator.defaultConference;

@RunWith(AndroidJUnit4.class)
public class CategoryFragmentTest {
  @Rule
  public FragmentTestRule fragmentTestRule = new FragmentTestRule();

  @Test
  public void renderSessionTimings(){
    CategoryFragment fragment = new CategoryFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(CategoryFragment.CATEGORY_VIEW_MODEL_EXTRA_KEY, new CategoryViewModel(Category.BELONG, defaultConference()));
    fragment.setArguments(bundle);
    fragmentTestRule.launch(fragment);
    onView(withId(R.id.session_list)).check(matches(isDisplayed()));
    onData(allOf(instanceOf(SessionViewModel.class))).inAdapterView(withId(R.id.session_list))
        .atPosition(0).onChildView(withId(R.id.duration)).check(matches(withText("03:30 - 04:30 (1h)")));

  }
}
