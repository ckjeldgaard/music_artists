package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestApplication extends MusicArtistsApplication {

    private SearchArtistModule mockSearchArtistModule;
    private SearchContract.SearchPresenter mockPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mockSearchArtistModule = mock(SearchArtistModule.class);
        this.mockPresenter = mock(SearchContract.SearchPresenter.class);

        when(mockSearchArtistModule.providePresenter()).thenReturn(mockPresenter);
    }

    public SearchContract.SearchPresenter mockPresenter() {
        return mockPresenter;
    }

    @Override
    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {
        return mockSearchArtistModule;
    }
}
