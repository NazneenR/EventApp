package thoughtworks.eventapp.view;

import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

public interface AgendaView {
  void render(ConferenceViewModel conferenceViewModel);
  void showProgressDialog();
  void dismissProgressDialog();
  void showConflictPopup();
  void showSessionAddedSuccessfully();
}
