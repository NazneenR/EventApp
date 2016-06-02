package thoughtworks.eventapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import thoughtworks.eventapp.R;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public class AgendaAdapter extends BaseAdapter {

  private ArrayList<SessionViewModel> sessions;
  private final LayoutInflater inflater;

  public AgendaAdapter(Context context, ArrayList<SessionViewModel> sessions) {
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.sessions = sessions;
  }

  @Override
  public int getCount() {
    return sessions.size();
  }

  @Override
  public Object getItem(int i) {
    return sessions.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewSource viewSource = null;
    if(convertView == null){
      convertView = inflater.inflate(R.layout.session, parent, false);
      viewSource = new ViewSource();
      viewSource.date = (TextView) convertView.findViewById(R.id.date);
      viewSource.location = (TextView) convertView.findViewById(R.id.location);
      viewSource.title = (TextView) convertView.findViewById(R.id.title);
      convertView.setTag(viewSource);
    }

    viewSource = (ViewSource) convertView.getTag();
    SessionViewModel session = sessions.get(position);
    viewSource.date.setText(session.getDisplayTime());
    viewSource.location.setText(session.getLocation());
    viewSource.title.setText(session.getName());
    return convertView;
  }

  protected class ViewSource {
    TextView date;
    TextView title;
    TextView location;
  }
}
