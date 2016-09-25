package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.SearchResponse;
import com.trifork.ckp.musicartists.searchartist.SearchArtistPresenter;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchArtistPresenterTest {

    @Mock
    private SearchContract.SearchArtistView searchArtistView;

    @Mock
    private LastFmApi api;

    @Captor
    private ArgumentCaptor<Call<SearchResponse>> cb;

    private SearchArtistPresenter searchArtistPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchArtistPresenter = new SearchArtistPresenter(searchArtistView, api);
    }

    @Test
    public void testDisplaySearchResult() throws Exception {
        List<Artist> expectedResult = new ArrayList<Artist>() {{
            //add(new Artist("Metallica"));
        }};

        cb.capture();
        Mockito.verify(api).searchArtist(Mockito.anyString());
        when(searchArtistView.searchInput()).thenReturn("metallica");

        searchArtistPresenter.searchArtist("metallica");

        verify(searchArtistView).showResultList(expectedResult);
    }
}