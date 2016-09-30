package com.trifork.ckp.musicartists.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistListItem {

    private final  String name;
    private final  String mbid;
    private final String url;
    @SerializedName("image") private final List<Image> images;

    public ArtistListItem(String name, String mbid, String url, List<Image> images) {
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

    public Image getImage(ImageSize size) {
        for (Image image : images) {
            if (image.getSize().equals(size)) {
                return image;
            }
        }
        return images.get(0);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
