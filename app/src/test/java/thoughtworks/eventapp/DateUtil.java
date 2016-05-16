package thoughtworks.eventapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  public static Date getDate(String dateString) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    return simpleDateFormat.parse(dateString);
  }
}
