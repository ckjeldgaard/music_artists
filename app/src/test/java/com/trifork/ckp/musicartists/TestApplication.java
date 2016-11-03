package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.injection.ViewArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;
import com.trifork.ckp.musicartists.viewartist.ViewArtistContract;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestApplication extends MusicArtistsApplication {

    private SearchArtistModule mockSearchArtistModule;
    private ViewArtistModule mockViewArtistModule;
    private SearchContract.SearchPresenter mockSearchPresenter;
    private ViewArtistContract.ArtistPresenter mockViewPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mockSearchArtistModule = mock(SearchArtistModule.class);
        this.mockSearchPresenter = mock(SearchContract.SearchPresenter.class);

        this.mockViewArtistModule = mock(ViewArtistModule.class);
        this.mockViewPresenter = mock(ViewArtistContract.ArtistPresenter.class);

        when(mockSearchArtistModule.providePresenter()).thenReturn(mockSearchPresenter);
        when(mockViewArtistModule.providePresenter()).thenReturn(mockViewPresenter);
    }

    public SearchContract.SearchPresenter mockSearchPresenter() {
        return mockSearchPresenter;
    }

    public ViewArtistContract.ArtistPresenter getMockViewArtistPresenter() {
        return mockViewPresenter;
    }

    @Override
    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {
        return mockSearchArtistModule;
    }

    @Override
    public ViewArtistModule getViewArtistModule(ViewArtistContract.ViewArtistView view) {
        return mockViewArtistModule;
    }
}
