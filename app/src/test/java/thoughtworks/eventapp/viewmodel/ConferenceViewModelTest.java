package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import java.text.ParseException;

import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.testdata.TestDataCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConferenceViewModelTest {

  @Test
  public void createConferenceViewModel() throws ParseException {
    Conference conference = TestDataCreator.defaultConference();
    ConferenceViewModel conferenceViewModel = new ConferenceViewModel(conference);
    assertThat(conferenceViewModel.categoryViewModels().size(),is(3));
    assertThat(conferenceViewModel.categoryViewModels().get(0).getCategoryNameInTitleCase(),is("Aspire"));

  }

}
