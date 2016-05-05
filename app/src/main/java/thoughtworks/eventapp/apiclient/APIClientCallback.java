package thoughtworks.eventapp.apiclient;

public interface APIClientCallback<T> {

  void onSuccess(T success);

  Class<T> getClassOfType();
}
