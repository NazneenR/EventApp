package thoughtworks.eventapp.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import thoughtworks.eventapp.view.fragment.AgendaTimelineFragment;
import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  public static final String SESSION_VIEW_MODEL = "thoughtworks.eventapp.view.sessionViewModels";
  private ConferenceViewModel conferenceViewModel;

  public ViewPagerAdapter(FragmentManager manager, ConferenceViewModel conferenceViewModel) {
    super(manager);
    this.conferenceViewModel = conferenceViewModel;
  }

  @Override
  public Fragment getItem(int position) {
    final AgendaTimelineFragment agendaTimelineFragment = new AgendaTimelineFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList(SESSION_VIEW_MODEL, (ArrayList<? extends Parcelable>) conferenceViewModel.sessionsAt(position));
    agendaTimelineFragment.setArguments(bundle);
    return agendaTimelineFragment;
  }

  @Override
  public int getCount() {
    return conferenceViewModel.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return conferenceViewModel.categoryAt(position);
  }
}