package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.Image;
import com.trifork.ckp.musicartists.searchartist.SearchArtistPresenter;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchArtistPresenterTest {

    @Mock
    private SearchContract.SearchArtistView view;

    @Mock
    private LastFmApi api;

    @Mock
    private List<Image> images;

    @Mock
    private Call<List<ArtistListItem>> call;

    @Captor
    private ArgumentCaptor<Callback<List<ArtistListItem>>> callbackCaptor;

    private SearchArtistPresenter searchArtistPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchArtistPresenter = new SearchArtistPresenter(view, api);
    }

    @Test
    public void testDisplaySearchResult() throws Exception {
        List<ArtistListItem> expectedResult = new ArrayList<ArtistListItem>() {{
            add(new ArtistListItem("Band 1", "mbid1", "url1", images));
            add(new ArtistListItem("Band 2", "mbid2", "url3", images));
        }};

        Response<List<ArtistListItem>> mockResponse = Response.success(expectedResult);
        when(view.searchInput()).thenReturn("artist");
        when(api.searchArtist(anyString())).thenReturn(call);
        searchArtistPresenter.searchArtist();

        verify(view).showLoading();
        verify(call).enqueue(callbackCaptor.capture());

        callbackCaptor.getValue().onResponse(call, mockResponse);

        verify(view).showResultList(expectedResult);
        verify(view).showContent();
    }

    @Test
    public void testSuccessfulResponseWithEmptyResultGeneratesError() throws Exception {
        Response<List<ArtistListItem>> mockResponse = Response.success(null);

        when(api.searchArtist(anyString())).thenReturn(call);
        searchArtistPresenter.searchArtist();

        verify(call).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onResponse(call, mockResponse);

        verify(view).showError(null);
    }

    @Test
    public void testFailure() throws Exception {
        Throwable throwable = mock(Throwable.class);

        when(view.searchInput()).thenReturn("artist");
        when(api.searchArtist(anyString())).thenReturn(call);
        searchArtistPresenter.searchArtist();

        verify(call).enqueue(callbackCaptor.capture());

        callbackCaptor.getValue().onFailure(call, throwable);

        verify(view).showError(throwable);
    }
}