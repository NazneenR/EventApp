package thoughtworks.eventapp.testdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

import static thoughtworks.eventapp.model.Category.BELONG;
import static thoughtworks.eventapp.model.Category.CREATE;

public class TestDataCreator {
  public static Session sessionFrom(String name, Category category, String startTimeAsString, String endTimeAsString) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date startTime = null;
    Date endTime = null;
    try {
      startTime = simpleDateFormat.parse("2016-05-23T"+startTimeAsString);
      endTime = simpleDateFormat.parse("2016-05-23T"+endTimeAsString);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Session("Craft", "Description of crafts", "2016-05-23",
        startTime, endTime, category);
  }

  public static final Session SESSION_1 = sessionFrom("Belong_Session", BELONG, "03:30:00+05:30", "04:30:00+05:30");
  public static final Session SESSION_2 = sessionFrom("Create_Session", CREATE, "03:30:00+05:30", "04:30:00+05:30");

  public static Conference defaultConference() {
    Conference conference = new Conference();
    Session session1 = SESSION_1;
    Session session2 = SESSION_2;
    List<Session> sessionList = new ArrayList<>();
    sessionList.add(session1);
    sessionList.add(session2);
    conference.setSessions(sessionList);
    return conference;
  }
}
