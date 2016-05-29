package thoughtworks.eventapp.view;

import thoughtworks.eventapp.viewmodel.ConferenceViewModel;

public interface AgendaView {
  void render(ConferenceViewModel conferenceViewModel);
}
