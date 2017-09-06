package id.cuxxie.newslistapps.Model.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by hendr on 9/6/2017.
 */
@JsonIgnoreProperties({ "urlsToLogos"})
public class Source implements Parcelable {
    String id;
    String name;
    String description;
    String category;
    String language;
    String country;
    String url;
    ArrayList<String> sortBysAvailable;

    public Source() {
    }

    public Source(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.category = in.readString();
        this.language = in.readString();
        this.country = in.readString();
        this.url = in.readString();
        in.readStringList(this.sortBysAvailable);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.category);
        dest.writeString(this.language);
        dest.writeString(this.country);
        dest.writeString(this.url);
        dest.writeStringList(this.sortBysAvailable);
    }

    public static final Creator CREATOR
            = new Creator() {

        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        public Source[] newArray(int size) {
            return new Source[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(ArrayList<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }
}
