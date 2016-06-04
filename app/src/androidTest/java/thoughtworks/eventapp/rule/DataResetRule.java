package thoughtworks.eventapp.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import thoughtworks.eventapp.model.SessionDAO;

public class DataResetRule implements TestRule {

  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try {
          SessionDAO.deleteAll(SessionDAO.class);
          base.evaluate();
        } finally {
          SessionDAO.deleteAll(SessionDAO.class);
        }
      }
    };
  }
}
