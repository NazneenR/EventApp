package thoughtworks.eventapp.testactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import thoughtworks.eventapp.R;

public class TestFragmentContainerActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_fragment_container);
  }
}
