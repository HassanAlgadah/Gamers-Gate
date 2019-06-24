package com.example.gamersgate;


import android.os.Parcel;
import android.os.Parcelable;

import okhttp3.internal.platform.Platform;

public class Platformm  {
    platform platform;

    public Platformm(platform platform) {
        this.platform = platform;
    }



    public platform getplatform() {
        return platform;
    }

    public void setplatform(platform p) {
        this.platform = p;
    }




}

class platform {
    private int id;
        private String name;
        private String slug;

    public platform(String name) {
        this.name = name;
    }

    public platform() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}

