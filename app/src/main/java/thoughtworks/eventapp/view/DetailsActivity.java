package thoughtworks.eventapp.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.model.Session;
import thoughtworks.eventapp.presenter.SessionDetailsPresenter;
import thoughtworks.eventapp.repository.SessionRepository;

public class DetailsActivity extends AppCompatActivity implements DetailView {

  public final static String SESSION_BUNDLE_PARAM= "thoughtworks.eventapp.session";
  private Session session;
  private SessionDetailsPresenter detailsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    session = getIntent().getExtras().getParcelable(SESSION_BUNDLE_PARAM);
    detailsPresenter = new SessionDetailsPresenter(this, new SessionRepository(), getResources());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle();
    setDescription();
  }

  public void saveSession(View view) {
    detailsPresenter.addSession(session);
  }

  @Override
  public void showConflictPopup(String currentSessionTitle, String sessionToAddTitle) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setTitle(R.string.agenda_overlap_dialog_title);
    alertDialogBuilder.setMessage(String.format("Timing of %s overlaps with timing of %s", currentSessionTitle, sessionToAddTitle));

    AlertDialog dialog = alertDialogBuilder.create();
    dialog.show();
  }

  @Override
  public void showSessionAddedSuccessfully(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    final ImageView save = (ImageView) findViewById(R.id.save);
    save.setImageResource(R.drawable.star_orange);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setTitle() {
    final TextView titleTextView = (TextView) findViewById(R.id.title);
    titleTextView.setText(session.getName());
  }

  private void setDescription() {
    final TextView descriptionTextView = (TextView) findViewById(R.id.description);
    descriptionTextView.setText(session.getDescription());
  }
}
