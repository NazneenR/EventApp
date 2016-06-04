package thoughtworks.eventapp.model;

import com.orm.SugarRecord;

import java.util.Date;

public class SessionDAO extends SugarRecord<SessionDAO> {

  private String title;
  private Date startTime;

  private Date endTime;

  public SessionDAO(){}

  public SessionDAO(String title, Date startTime, Date endTime){
    this.title = title;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public static SessionDAO createFrom(Session session){
    return new SessionDAO(session.getName(), session.getStartTime(), session.getEndTime());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SessionDAO that = (SessionDAO) o;

    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null)
      return false;
    return endTime != null ? endTime.equals(that.endTime) : that.endTime == null;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
    result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
    return result;
  }

  public Date getStartTime() {
    return startTime;
  }

  public Date getEndTime() {
    return endTime;
  }
}
