package com.trifork.ckp.musicartists.injection;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SearchArtistModuleTest {
    @Test
    public void testProvidePresenter() throws Exception {
        SearchContract.SearchArtistView view = mock(SearchContract.SearchArtistView.class);
        LastFmApi api = mock(LastFmApi.class);

        SearchArtistModule module = new SearchArtistModule(view, api);
        assertThat(module.providePresenter(), is(instanceOf(SearchContract.SearchPresenter.class)));
    }

}