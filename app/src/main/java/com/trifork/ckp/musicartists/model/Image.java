package com.trifork.ckp.musicartists.model;

import com.google.gson.annotations.SerializedName;

public final class Image {
    @SerializedName("#text")
    private final String url;
    @SerializedName("size")
    private final ImageSize size;

    public Image(String url, ImageSize size) {
        this.url = url;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public ImageSize getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!url.equals(image.url)) return false;
        return size == image.size;

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{size='" + size + '\'' + ", url='" + url + "\'}";
    }
}
