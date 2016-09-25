package com.trifork.ckp.musicartists.model;

public final class ArtistListItem {

    private final  String name;
    private final  String mbid;
    private final String url;
    //private final Image image;


    public ArtistListItem(String name, String mbid, String url) {
        this.name = name;
        this.mbid = mbid;
        this.url = url;
    }

    /*public ArtistListItem(String name, String mbid, String url, Image image) {
        this.name = name;
        this.mbid = mbid;
        this.url = url;
        this.image = image;
    }*/

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    /*public Image getImage() {
        return image;
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistListItem that = (ArtistListItem) o;

        if (!name.equals(that.name)) return false;
        if (!mbid.equals(that.mbid)) return false;
        if (!url.equals(that.url)) return false;
        return image.equals(that.image);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + mbid.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + image.hashCode();
        return result;
    }*/

    @Override
    public String toString() {
        return this.name;
    }
}
