package com.trifork.ckp.musicartists.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class Artist {

    private final String name;
    private final String mbid;
    private final String url;

    @SerializedName("image")
    private final List<Image> images;

    public Artist(String name, String mbid, String url, List<Image> images) {
        this.name = name;
        this.mbid = mbid;
        this.url = url;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public List<Image> getImages() {
        return images;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artist artist = (Artist) o;
        return name.equals(artist.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }*/
}
