package com.trifork.ckp.musicartists.injection;


import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.viewartist.ViewArtistContract;
import com.trifork.ckp.musicartists.viewartist.ViewArtistPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewArtistModule {
    private final ViewArtistContract.ViewArtistView view;
    private final LastFmApi api;

    public ViewArtistModule(ViewArtistContract.ViewArtistView view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Provides
    public ViewArtistContract.ArtistPresenter providePresenter(){
        return new ViewArtistPresenter(view, api);
    }
}