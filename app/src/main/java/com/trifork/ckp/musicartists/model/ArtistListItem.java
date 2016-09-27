package com.trifork.ckp.musicartists.model;

public final class ArtistListItem {

    private final  String name;
    private final  String mbid;
    private final String url;

    public ArtistListItem(String name, String mbid, String url) {
        this.name = name;
        this.mbid = mbid;
        this.url = url;
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

    @Override
    public String toString() {
        return this.name;
    }
}
