package thoughtworks.eventapp.viewmodel;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

public class ConferenceViewModel {

  private List<Category> categories;
  private List<List<SessionViewModel>> sessionViewModels;

  private ConferenceViewModel(List<Category> categories, List<List<SessionViewModel>> sessionViewModels) {
    this.categories = categories;
    this.sessionViewModels = sessionViewModels;
  }

  public String categoryAt(int index) {
    return categories.get(index).name();
  }

  public List<SessionViewModel> sessionsAt(int index) {
    return sessionViewModels.get(index);
  }

  public static ConferenceViewModel createFrom(List<Category> categories, Conference conference) {
    List<List<SessionViewModel>> sessionViewModels = new ArrayList<>();
    for (Category category : Category.values()) {
      sessionViewModels.add(getSessionViewModelsByCategory(conference, category));
    }
    return new ConferenceViewModel(categories, sessionViewModels);
  }

  @NonNull
  private static ArrayList<SessionViewModel> getSessionViewModelsByCategory(Conference conference, Category category) {
    ArrayList<SessionViewModel> sessionViewModels = new ArrayList<>();
    final List<Session> filteredSessions = conference.filterByCategory(category);
    for (Session session : filteredSessions) {
      sessionViewModels.add(new SessionViewModel(session));
    }
    return sessionViewModels;
  }

  public int size() {
    return categories.size();
  }

  public int indexOf(Category category) {
    return categories.indexOf(category);
  }
}
