package thoughtworks.eventapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.view.fragment.AgendaTimelineFragment;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  private final List<ArrayList<SessionViewModel>> sessionViewModels;
  private List<String> titleList = new ArrayList<>();

  public ViewPagerAdapter(FragmentManager manager, List<ArrayList<SessionViewModel>> sessionViewModels) {
    super(manager);
    this.sessionViewModels = sessionViewModels;
    //TODO: Remove hardcodings
    titleList.add(0, "Create");
    titleList.add(1, "Aspire");
    titleList.add(2, "Belong");
  }

  @Override
  public Fragment getItem(int position) {
    final AgendaTimelineFragment agendaTimelineFragment = new AgendaTimelineFragment();
    Bundle bundle = new Bundle();
    //TODO: Extract into constant
    bundle.putParcelableArrayList("sessionViewModels", sessionViewModels.get(0));
    agendaTimelineFragment.setArguments(bundle);
    return agendaTimelineFragment;
  }

  @Override
  public int getCount() {
    return sessionViewModels.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titleList.get(position);
  }
}