package thoughtworks.eventapp;

import android.app.Application;

import com.firebase.client.Firebase;

public class EventApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Firebase.setAndroidContext(this);
  }
}
