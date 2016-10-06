package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@Ignore
public class MusicArtistsApplicationTest {
    @Test
    public void testGetSearchArtistModuleReturnsObject() throws Exception {
        SearchContract.SearchArtistView view = mock(SearchContract.SearchArtistView.class);

        MusicArtistsApplication application = new MusicArtistsApplication();
        assertThat(application.getSearchArtistModule(view), is(instanceOf(SearchArtistModule.class)));
    }
}