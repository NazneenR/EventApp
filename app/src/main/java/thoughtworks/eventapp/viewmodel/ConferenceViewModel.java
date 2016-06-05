package thoughtworks.eventapp.viewmodel;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

public class ConferenceViewModel {

  private List<CategoryViewModel> categoryViewModels;

  private ConferenceViewModel(List<CategoryViewModel> categoryViewModels) {
    this.categoryViewModels = categoryViewModels;
  }

  public static ConferenceViewModel createFrom(List<Category> categories, Conference conference) {
    List<CategoryViewModel> categoryViewModels = new ArrayList<>();
    for (Category category : Category.values()) {
      final List<Session> filteredSessions = conference.filterByCategory(category);
      CategoryViewModel categoryViewModel = CategoryViewModel.createFrom(category, filteredSessions);
      categoryViewModels.add(categoryViewModel);
    }
    return new ConferenceViewModel(categoryViewModels);
  }

  public CategoryViewModel getCategoryViewModelAt(int position) {
    return categoryViewModels.get(position);
  }

  public int sizeOfCategoryViewModels() {
    return categoryViewModels.size();
  }
}
