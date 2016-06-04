package thoughtworks.eventapp.view;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.model.SessionDAO;
import thoughtworks.eventapp.rule.DataResetRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static thoughtworks.eventapp.utils.DateUtil.getDate;

public class DetailsActivityTest {

  @Rule
  public ActivityTestRule<DetailsActivity> activityTestRule = new
      ActivityTestRule<>(DetailsActivity.class, false, false);

  @Rule
  public DataResetRule dataResetRule = new DataResetRule();

  @Before
  public void setup() throws ParseException {
    Session sessionInTrackTwo = new Session("Keynote", "Try your hand at craft", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-23T18:15:00+05:30"), Category.CREATE, "Pre function area");

    SessionDAO.createFrom(sessionInTrackTwo).save();
  }

  @Test
  public void showDialogIfConflictingSessionExists() throws ParseException {
    Intent intent = new Intent();
    Session sessionInTrackOne = new Session("Craft", "Try your hand at craft", "2016-05-23",
        getDate("2016-05-23T17:15:00+05:30"), getDate("2016-05-23T20:15:00+05:30"), Category.CREATE, "Ballroom");
    intent.putExtra("session", sessionInTrackOne);

    activityTestRule.launchActivity(intent);

    onView(withId(R.id.save)).perform(click());

    onView(withText("Timing of Keynote overlaps with timing of Craft")).check(matches(isDisplayed()));
  }

  @Test
  public void showSuccessfulAdditionOfSessionMessage() throws ParseException {
    Intent intent = new Intent();
    Session sessionInTrackOne = new Session("Craft", "Try your hand at craft", "2016-05-24",
        getDate("2016-05-24T17:15:00+05:30"), getDate("2016-05-24T20:15:00+05:30"), Category.CREATE, "Ballroom");
    intent.putExtra("session", sessionInTrackOne);

    activityTestRule.launchActivity(intent);

    onView(withId(R.id.save)).perform(click());

    onView(withText("Session successfully saved")).inRoot(withDecorView(not(activityTestRule.
        getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
  }
}