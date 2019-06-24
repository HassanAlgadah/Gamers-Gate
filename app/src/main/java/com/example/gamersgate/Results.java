package com.example.gamersgate;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Results implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name ;
    private String background_image ;
    private double rating ;
    private List<Platformm> platforms;
    private String released;
    private String Discrption;
    private String slug;
    private List<genre> genres;
    private String description_raw;

    public Results() {
    }

    public Results(Parcel in) {
        this.name = in.readString();
        this.background_image = in.readString();
        this.rating = in.readDouble();
        platforms = new ArrayList<Platformm>();
        this.released = in.readString();
        Discrption = in.readString();
        this.slug = in.readString();
        genres = new ArrayList<genre>();

    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Platformm> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platformm> platforms) {
        this.platforms = platforms;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public List<genre> getGenres() {
        return genres;
    }

    public void setGenres(List<genre> genres) {
        this.genres = genres;
    }

    public String getDescription_raw() {
        return description_raw;
    }

    public void setDescription_raw(String description_raw) {
        this.description_raw = description_raw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getbackground_image() {
        return background_image;
    }

    public void setbackground_image(String background_image) {
        this.background_image = background_image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Platformm> getplatforms() {
        return platforms;
    }

    public void setplatforms(List<Platformm> platforms) {
        this.platforms = platforms;
    }

    public String getreleased() {
        return released;
    }

    public void setreleased(String released) {
        this.released = released;
    }

    public String getDiscrption() {
        return Discrption;
    }

    public void setDiscrption(String discrption) {
        Discrption = discrption;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<genre> getGenre() {
        return genres;
    }

    public void setGenre(List<genre> genre) {
        this.genres = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(background_image);
        dest.writeDouble(rating);
        dest.writeString(released);
        dest.writeString(Discrption);
        dest.writeString(slug);


    }
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
class genre {
    private String name;

    public genre() {
    }

    public String getName() {
        return name;
    }
}

class game{
    private List<Results> results;
    private String description_raw;

    public String getDescription_raw() {
        return description_raw;
    }

    public void setDescription_raw(String description_raw) {
        this.description_raw = description_raw;
    }

    public game() {
    }

    public List<Results> getResults() {
        return results;
    }
}
