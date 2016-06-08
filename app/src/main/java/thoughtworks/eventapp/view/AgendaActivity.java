package thoughtworks.eventapp.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.presenter.AgendaPresenter;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

public class AgendaActivity extends AppCompatActivity implements AgendaView {

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agenda);
    fetchSessions();
  }

  private void fetchSessions() {
    new AgendaPresenter(getApiClient(), this).fetchSessions();
  }

  @NonNull
  protected APIClient getApiClient() {
    return new APIClient(this);
  }

  @Override
  public void render(ConferenceViewModel conferenceViewModel) {
    setupTabs(conferenceViewModel);
  }

  @Override
  public void showProgressDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage(getResources().getString(R.string.loading));
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void dismissProgressDialog() {
    progressDialog.dismiss();
  }

  @Override
  public void showDialog(String message) {
    AlertDialog alertDialog = new AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            finish();
          }
        })
        .create();
    alertDialog.show();
  }

  private void setupTabs(ConferenceViewModel conferenceViewModel) {
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), conferenceViewModel);
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}