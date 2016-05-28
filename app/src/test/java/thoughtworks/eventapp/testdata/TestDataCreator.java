package thoughtworks.eventapp.testdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Session;

public class TestDataCreator {
  public static Session sessionFrom(String name, Category category, String startTimeAsString, String endTimeAsString) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date startTime = simpleDateFormat.parse("2016-05-23T"+startTimeAsString);
    Date endTime = simpleDateFormat.parse("2016-05-23T"+endTimeAsString);
    return new Session("Craft", "Description of crafts", "2016-05-23",
        startTime, endTime, Category.BELONG);
  }

}
