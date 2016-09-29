package com.trifork.ckp.musicartists;

import android.app.Application;

import com.trifork.ckp.musicartists.api.LastFmClient;
import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

public class MusicArtistsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {
        return new SearchArtistModule(view, new LastFmClient().getServiceApi());
    }
}
