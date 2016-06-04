package thoughtworks.eventapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.view.AgendaAdapter;
import thoughtworks.eventapp.view.ViewPagerAdapter;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaTimelineFragment extends Fragment {
  private ArrayList<SessionViewModel> sessionViewModels;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    sessionViewModels = getArguments().getParcelableArrayList(ViewPagerAdapter.SESSION_VIEW_MODEL);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.sessions_layout, container, false);
    ListView listView = (ListView) view.findViewById(R.id.list_view);
    listView.setAdapter(new AgendaAdapter(getActivity(), sessionViewModels));
    return view;
  }
}