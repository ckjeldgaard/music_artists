package com.trifork.ckp.musicartists.model;

public class ArtistResponse {

    private final Artist artist;

    public ArtistResponse(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }
}
