package com.trifork.ckp.musicartists.api;

import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.ArtistListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmApi {
    @GET("?method=artist.search&format=json&api_key=4b3207e16b62f1ce0e50e2d6d0e6b391")
    Call<List<ArtistListItem>> searchArtist(@Query("artist") String artist);

    @GET("?method=artist.getinfo&format=json&api_key=4b3207e16b62f1ce0e50e2d6d0e6b391")
    Call<Artist> getArtist(@Query("mbid") String mbid);
}