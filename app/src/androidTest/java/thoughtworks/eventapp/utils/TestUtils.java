package thoughtworks.eventapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

  //TODO: This method is duplicated with DateUtil.getDate(). Also SimpleDateFormat behaves differently for Junit and Android Test.
  public static Date getDate(String dateString) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    return simpleDateFormat.parse(dateString);
  }
}
