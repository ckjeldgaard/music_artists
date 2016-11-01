package com.trifork.ckp.musicartists.viewartist;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.ArtistResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewArtistPresenter implements ViewArtistContract.ArtistPresenter, Callback<ArtistResponse> {

    private final ViewArtistContract.ViewArtistView view;
    private final LastFmApi api;

    public ViewArtistPresenter(ViewArtistContract.ViewArtistView view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void getArtist() {
        this.view.showLoading();
        this.api.getArtist(this.view.getArtistMbid()).enqueue(this);
    }

    @Override
    public void onResponse(Call<ArtistResponse> call, Response<ArtistResponse> response) {
        this.view.setArtist(response.body().getArtist());
        this.view.showContent();
    }

    @Override
    public void onFailure(Call<ArtistResponse> call, Throwable t) {
        this.view.showError(t);
    }
}
