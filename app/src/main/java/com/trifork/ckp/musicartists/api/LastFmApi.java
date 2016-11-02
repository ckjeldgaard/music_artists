package com.trifork.ckp.musicartists.api;

import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.ArtistResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmApi {
    @GET("?method=artist.search&format=json")
    Call<List<ArtistListItem>> searchArtist(@Query("artist") String artist);

    @GET("?method=artist.getinfo&format=json")
    Call<ArtistResponse> getArtist(@Query("mbid") String mbid);
}