package com.trifork.ckp.musicartists.injection;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.searchartist.SearchArtistPresenter;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchArtistModule {

    private final SearchContract.SearchArtistView view;
    private final LastFmApi api;

    public SearchArtistModule(SearchContract.SearchArtistView view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Provides
    public SearchContract.SearchPresenter providePresenter(){
        return new SearchArtistPresenter(view, api);
    }
}
