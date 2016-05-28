package thoughtworks.eventapp.viewmodel;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static thoughtworks.eventapp.model.Category.BELONG;
import static thoughtworks.eventapp.model.Category.CREATE;
import static thoughtworks.eventapp.testdata.TestDataCreator.sessionFrom;

public class ConferenceViewModelTest {

  @Test
  public void createConferenceViewModel() throws ParseException {
    Session session1 = sessionFrom("Belong_Session", BELONG, "03:30:00+05:30", "04:30:00+05:30");
    Session session2 = sessionFrom("Create_Session", CREATE, "03:30:00+05:30", "04:30:00+05:30");
    List<Session> sessionList = new ArrayList<>();
    sessionList.add(session1);
    sessionList.add(session2);
    Conference conference = new Conference();
    conference.setSessions(sessionList);

    ConferenceViewModel conferenceViewModel = new ConferenceViewModel(conference);
    assertThat(conferenceViewModel.categoryViewModels().size(),is(3));
    assertThat(conferenceViewModel.categoryViewModels().get(0).getCategoryNameInTitleCase(),is("Aspire"));

  }
}
