package thoughtworks.eventapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.adapter.ViewPagerAdapter;
import thoughtworks.eventapp.apiclient.APIClient;
import thoughtworks.eventapp.presenter.AgendaPresenter;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaActivity extends AppCompatActivity implements AgendaView {

  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agenda);
    setupToolbar();
    fetchSessions();
  }

  private void fetchSessions() {
    new AgendaPresenter(getApiClient(), this).fetchSessions();
  }

  @NonNull
  protected APIClient getApiClient() {
    return new APIClient();
  }

  @Override
  public void render(List<ArrayList<SessionViewModel>> sessionViewModels) {
    setupViewPager(sessionViewModels);
    setupTabs();
  }

  private void setupTabs() {
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(List<ArrayList<SessionViewModel>> sessionViewModels) {
    viewPager = (ViewPager) findViewById(R.id.viewpager);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), sessionViewModels);
    viewPager.setAdapter(adapter);
  }

  private void setupToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }
}