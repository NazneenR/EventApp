package thoughtworks.eventapp.apiclient;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class APIClient {
  public <T> void get(String url, final APIClientCallback<T> apiClientCallback) {
    Firebase firebase = new Firebase(url);
    firebase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        T response = snapshot.getValue(apiClientCallback.getClassOfType());
        apiClientCallback.onSuccess(response);
      }

      @Override
      public void onCancelled(FirebaseError error) {
      }
    });
  }
}
