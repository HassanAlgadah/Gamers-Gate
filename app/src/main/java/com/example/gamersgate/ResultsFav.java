package com.example.gamersgate;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import java.util.List;

@Entity
public class ResultsFav {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String background_image;
    private double rating;
    private String released;
    private String Discrption;
    private String slug;

    public ResultsFav() {
    }

    public ResultsFav( String name, String background_image, double rating, String released, String slug ) {
        this.name = name;
        this.background_image = background_image;
        this.rating = rating;
        this.released = released;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }



    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
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

}
