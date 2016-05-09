package thoughtworks.eventapp.view;

import java.util.List;
import thoughtworks.eventapp.viewmodel.SessionViewModel;

public interface AgendaView {
  void render(List<SessionViewModel> sessions);
}
