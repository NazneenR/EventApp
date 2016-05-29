package thoughtworks.eventapp.view;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.viewmodel.SessionViewModel;

public interface AgendaView {
  void render(List<ArrayList<SessionViewModel>> sessions);
  void showProgressDialog();
  void dismissProgressDialog();
  void showConflictPopup();
  void showSessionAddedSuccessfully();
}
