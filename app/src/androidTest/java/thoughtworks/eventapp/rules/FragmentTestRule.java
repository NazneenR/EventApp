package thoughtworks.eventapp.rules;

import android.app.Fragment;
import android.support.test.rule.ActivityTestRule;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.testactivity.TestFragmentContainerActivity;

public class FragmentTestRule extends ActivityTestRule<TestFragmentContainerActivity>{
  public FragmentTestRule() {
    super(TestFragmentContainerActivity.class);
  }

  public void launch(Fragment fragment){
    getActivity().getFragmentManager().beginTransaction().add(R.id.test_fragment_container, fragment).commit();
  }



}
