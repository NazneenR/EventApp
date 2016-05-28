package thoughtworks.eventapp.viewmodel;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

public class CategoryViewModel {
  private final Category category;
  private final List<Session> sessions;

  public CategoryViewModel(Category category, Conference conference) {
    this.category = category;
    sessions = conference.filterByCategory(category);
  }

  public String getCategoryNameInTitleCase() {
    String name = category.name();
    return  name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
  }

  public ArrayList<SessionViewModel> getSessions() {
    ArrayList<SessionViewModel> viewModels = new ArrayList<>();
    for (Session session : sessions) {
      viewModels.add(new SessionViewModel(session));
    }
    return viewModels;
  }
}
