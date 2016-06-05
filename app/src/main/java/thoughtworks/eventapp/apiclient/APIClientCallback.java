package thoughtworks.eventapp.apiclient;

import android.support.annotation.NonNull;

public interface APIClientCallback<T> {

  void onSuccess(T success);

  void onFailure(Exception e);

  //TODO: See whether NonNull check can be identified in android lint.
  @NonNull
  Class<T> getClassOfType();
}
