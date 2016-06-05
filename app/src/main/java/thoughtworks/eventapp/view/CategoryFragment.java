package thoughtworks.eventapp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.adapter.SessionListAdapter;
import thoughtworks.eventapp.viewmodel.CategoryViewModel;


public class CategoryFragment extends Fragment{
  public static String CATEGORY_VIEW_MODEL_EXTRA_KEY = "thoughtworks.eventapp.view.categoryviewmodel";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    CategoryViewModel viewModel = getArguments().getParcelable(CATEGORY_VIEW_MODEL_EXTRA_KEY);
    View view = inflater.inflate(R.layout.agenda_fragment, container, false);
    ListView sessionListView = (ListView) view.findViewById(R.id.session_list);
    sessionListView.setAdapter(new SessionListAdapter(viewModel.getSessions()));
    return view;
  }
}
