package thoughtworks.eventapp.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;


public class CategoryViewModelTest {

  private CategoryViewModel categoryViewModel;

  @Before
  public void setup(){
    final Session session2 = new Session("Create_Session", "Try your hand at craft", "24-05-2016", null, null, Category.CREATE);
    final ArrayList<Session> filteredSessions = new ArrayList<Session>() {{
      add(session2);
    }};
    Conference conference = mock(Conference.class);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        return filteredSessions;
      }
    }).when(conference).filterByCategory(Category.CREATE);
    categoryViewModel = new CategoryViewModel(Category.CREATE, conference);

  }
  @Test
  public void displayCategoryNameInTitleCase(){
    assertThat(categoryViewModel.getCategoryNameInTitleCase(), is("Create"));
  }

  @Test
  public void displayListOfTalksForCategory(){

    assertThat(categoryViewModel.getSessions().size(), is(1));
    assertThat(categoryViewModel.getSessions().get(0).getName(), is("Create_Session"));
  }


}
