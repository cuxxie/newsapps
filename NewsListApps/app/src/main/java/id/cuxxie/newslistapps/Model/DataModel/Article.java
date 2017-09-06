package id.cuxxie.newslistapps.Model.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hendr on 9/6/2017.
 */

public class Article implements Parcelable {

    String author;
    String description;
    String title;
    String url;
    String urlToImage;
    String publishedAt;

    public Article(Parcel in) {
        this.author = in.readString();
        this.description = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.urlToImage = in.readString();
        this.publishedAt = in.readString();
    }

    public Article() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.description);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.urlToImage);
        dest.writeString(this.publishedAt);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    public static final Creator CREATOR
            = new Creator() {

        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
