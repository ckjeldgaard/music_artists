package com.trifork.ckp.musicartists.model;

public final class ArtistResponse {

    private final Artist artist;

    public ArtistResponse(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }
}
