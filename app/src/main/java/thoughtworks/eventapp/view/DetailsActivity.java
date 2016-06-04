package thoughtworks.eventapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.presenter.SessionDetailsPresenter;
import thoughtworks.eventapp.repository.SessionRepository;

public class DetailsActivity extends Activity implements DetailView {

  private Session session;
  private AlertDialog.Builder alertDialogBuilder;
  private SessionDetailsPresenter detailsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    session = getIntent().getExtras().getParcelable("session");
    detailsPresenter = new SessionDetailsPresenter(this, new SessionRepository(), getResources());
  }

  public void saveSession(View view) {
    detailsPresenter.addSession(session);
  }

  @Override
  public void showConflictPopup(String currentSessionTitle, String sessionToAddTitle) {
    alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setTitle(R.string.agenda_overlap_dialog_title);
    alertDialogBuilder.setMessage(String.format("Timing of %s overlaps with timing of %s", currentSessionTitle, sessionToAddTitle));

    AlertDialog dialog = alertDialogBuilder.create();
    dialog.show();
  }

  @Override
  public void showSessionAddedSuccessfully(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
