package thoughtworks.eventapp.rule;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import thoughtworks.eventapp.EventAppAndroidJUnitRunner;

public class ActivityUnitTestRule<T extends Activity> extends ActivityTestRule<T> {
  private EventAppAndroidJUnitRunner.ActivityProvider<T> activityProvider;

  public ActivityUnitTestRule(EventAppAndroidJUnitRunner.ActivityProvider<T> activityProvider, Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
    super(activityClass, initialTouchMode, launchActivity);
    this.activityProvider = activityProvider;
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return new ActivityInstrumentationStatement(super.apply(base, description));
  }

  private class ActivityInstrumentationStatement extends Statement {
    private Statement base;

    public ActivityInstrumentationStatement(Statement base) {
      this.base = base;
    }

    @Override
    public void evaluate() throws Throwable {
      EventAppAndroidJUnitRunner instrumentation = (EventAppAndroidJUnitRunner) InstrumentationRegistry.getInstrumentation();
      instrumentation.createActivityUsing(activityProvider);
      try {
        base.evaluate();
      } finally {
        instrumentation.reset();
      }
    }
  }
}