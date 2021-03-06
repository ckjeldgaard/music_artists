package com.trifork.ckp.musicartists.searchartist;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.ArtistListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArtistPresenter implements SearchContract.SearchPresenter, Callback<List<ArtistListItem>> {

    private final SearchContract.SearchArtistView view;
    private final LastFmApi api;

    public SearchArtistPresenter(SearchContract.SearchArtistView view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void searchArtist() {
        this.view.showLoading();
        this.api.searchArtist(view.searchInput()).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ArtistListItem>> call, Response<List<ArtistListItem>> response) {
        if (response.body() != null) {
            this.view.showResultList(response.body());
            this.view.showContent();
        } else {
            this.onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<List<ArtistListItem>> call, Throwable t) {
        this.view.showError(t);
    }
}
