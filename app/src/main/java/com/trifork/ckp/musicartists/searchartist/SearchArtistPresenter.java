package com.trifork.ckp.musicartists.searchartist;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArtistPresenter implements SearchContract.SearchArtistUserActions, Callback<List<ArtistListItem>> {

    public static final String TAG = SearchArtistPresenter.class.getSimpleName();

    private final SearchContract.SearchArtistView view;
    private final LastFmApi api;

    public SearchArtistPresenter(SearchContract.SearchArtistView view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void searchArtist(String artist) {

        Call<List<ArtistListItem>> call = this.api.searchArtist("metallica");
        call.enqueue(this);

        List<Artist> resultList = new ArrayList<Artist>() {{
            add(new Artist("Metallica"));
        }};
        this.view.showResultList(resultList);
    }


    @Override
    public void onResponse(Call<List<ArtistListItem>> call, Response<List<ArtistListItem>> response) {

    }

    @Override
    public void onFailure(Call<List<ArtistListItem>> call, Throwable t) {

    }
}
