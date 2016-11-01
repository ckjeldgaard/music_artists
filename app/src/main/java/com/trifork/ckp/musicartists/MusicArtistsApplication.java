package com.trifork.ckp.musicartists;

import android.app.Application;

import com.trifork.ckp.musicartists.api.LastFmClient;
import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.injection.ViewArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;
import com.trifork.ckp.musicartists.viewartist.ViewArtistContract;

public class MusicArtistsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {
        return new SearchArtistModule(view, new LastFmClient().getServiceApi(true));
    }

    public ViewArtistModule getViewArtistModule(ViewArtistContract.ViewArtistView view) {
        return new ViewArtistModule(view, new LastFmClient().getServiceApi(false));
    }
}
