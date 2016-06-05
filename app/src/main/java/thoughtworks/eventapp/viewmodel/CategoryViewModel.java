package thoughtworks.eventapp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.eventapp.model.Category;
import thoughtworks.eventapp.model.Conference;
import thoughtworks.eventapp.model.Session;

public class CategoryViewModel implements Parcelable{
  private final Category category;
  private final List<Session> sessions;

  public CategoryViewModel(Category category, Conference conference) {
    this.category = category;
    sessions = conference.filterByCategory(category);
  }

  public String getCategoryNameInTitleCase() {
    String name = category.name();
    return  name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
  }

  public ArrayList<SessionViewModel> getSessions() {
    ArrayList<SessionViewModel> viewModels = new ArrayList<>();
    for (Session session : sessions) {
      viewModels.add(new SessionViewModel(session));
    }
    return viewModels;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.category == null ? -1 : this.category.ordinal());
    dest.writeList(this.sessions);
  }

  protected CategoryViewModel(Parcel in) {
    int tmpCategory = in.readInt();
    this.category = tmpCategory == -1 ? null : Category.values()[tmpCategory];
    this.sessions = new ArrayList<Session>();
    in.readList(this.sessions, Session.class.getClassLoader());
  }

  public static final Creator<CategoryViewModel> CREATOR = new Creator<CategoryViewModel>() {
    @Override
    public CategoryViewModel createFromParcel(Parcel source) {
      return new CategoryViewModel(source);
    }

    @Override
    public CategoryViewModel[] newArray(int size) {
      return new CategoryViewModel[size];
    }
  };
}
