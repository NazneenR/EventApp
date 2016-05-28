package thoughtworks.eventapp.viewmodel;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;

public class ConferenceViewModel {

  private final List<CategoryViewModel> categoryViewModels;

  public ConferenceViewModel(Conference conference) {
    categoryViewModels = new ArrayList<>();
    for (Category category : Category.values()) {
      categoryViewModels.add(new CategoryViewModel(category, conference));
    }
  }

  public List<CategoryViewModel> categoryViewModels() {
    return categoryViewModels;
  }
}
