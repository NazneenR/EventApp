package thoughtworks.eventapp;

import com.firebase.client.Firebase;
import com.orm.SugarApp;

public class EventApplication extends SugarApp {

  @Override
  public void onCreate() {
    super.onCreate();
    Firebase.setAndroidContext(this);
  }
}
