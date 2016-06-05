package thoughtworks.eventapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.viewmodel.SessionViewModel;


public class SessionListAdapter extends BaseAdapter {


  private final List<SessionViewModel> sessionViewModels;

  public SessionListAdapter(List<SessionViewModel> sessionViewModels) {
    this.sessionViewModels = sessionViewModels;
  }

  @Override
  public int getCount() {
    return sessionViewModels.size();
  }

  @Override
  public SessionViewModel getItem(int position) {
    return sessionViewModels.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null){
      convertView = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.list_item_session, parent, false);
    }
    SessionViewModel viewModel = getItem(position);

    TextView durationView = (TextView) convertView.findViewById(R.id.duration);
    durationView.setText(viewModel.formattedSessionTime());
    return convertView;
  }
}
