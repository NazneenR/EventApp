package thoughtworks.eventapp.viewmodel;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;

public class CategoryViewModel {

  private Category category;
  private List<SessionViewModel> sessionViewModels;

  private CategoryViewModel(Category category, List<SessionViewModel> sessionViewModels) {
    this.category = category;
    this.sessionViewModels = sessionViewModels;
  }

  public List<SessionViewModel> getSessionViewModels() {
    return sessionViewModels;
  }

  public String getCategoryName() {
    return category.name();
  }

  public static CategoryViewModel createFrom(Category category, List<Session> sessions) {
    return new CategoryViewModel(category, createSessionViewModelsFrom(category, sessions));
  }

  @NonNull
  private static List<SessionViewModel> createSessionViewModelsFrom(Category category, List<Session> sessions) {
    List<SessionViewModel> sessionViewModels = new ArrayList<>();
    for (Session session : sessions) {
      sessionViewModels.add(SessionViewModel.createFrom(session));
    }
    return sessionViewModels;
  }
}
