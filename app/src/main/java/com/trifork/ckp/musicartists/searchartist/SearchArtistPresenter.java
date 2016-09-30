package com.trifork.ckp.musicartists.searchartist;

import android.util.Log;

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
        String inp = view.searchInput();
        this.api.searchArtist(inp).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ArtistListItem>> call, Response<List<ArtistListItem>> response) {
        Log.d("Presenter", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
        this.view.showResultList(response.body());
    }

    @Override
    public void onFailure(Call<List<ArtistListItem>> call, Throwable t) {

    }
}
