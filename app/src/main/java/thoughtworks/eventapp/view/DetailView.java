package thoughtworks.eventapp.view;

public interface DetailView {
  void showConflictPopup(String name, String sessionToAddName);
  void showSessionAddedSuccessfully(String message);
}
