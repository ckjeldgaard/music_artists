package com.trifork.ckp.musicartists.injection;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.searchartist.SearchContract;
import com.trifork.ckp.musicartists.viewartist.ViewArtistContract;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ModuleTest {
    @Test
    public void testProvideSearchPresenter() throws Exception {
        SearchContract.SearchArtistView view = mock(SearchContract.SearchArtistView.class);
        LastFmApi api = mock(LastFmApi.class);

        SearchArtistModule module = new SearchArtistModule(view, api);
        assertThat(module.providePresenter(), is(instanceOf(SearchContract.SearchPresenter.class)));
    }

    @Test
    public void testProvideDisplayArtistPresenter() throws Exception {
        ViewArtistContract.ViewArtistView view = mock(ViewArtistContract.ViewArtistView.class);
        LastFmApi api = mock(LastFmApi.class);

        ViewArtistModule module = new ViewArtistModule(view, api);
        assertThat(module.providePresenter(), is(instanceOf(ViewArtistContract.ArtistPresenter.class)));
    }
}