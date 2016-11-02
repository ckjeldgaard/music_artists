package com.trifork.ckp.musicartists.viewartist;

import com.trifork.ckp.musicartists.api.LastFmApi;
import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.ArtistResponse;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewArtistPresenterTest {

    @Mock
    private ViewArtistContract.ViewArtistView view;

    @Mock
    private LastFmApi api;

    @Mock
    private Call<ArtistResponse> call;

    @Captor
    private ArgumentCaptor<Callback<ArtistResponse>> callbackCaptor;

    private ViewArtistPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ViewArtistPresenter(view, api);
    }

    @Test
    public void testDisplayArtist() throws Exception {
        ArtistResponse artist = mock(ArtistResponse.class);

        Response<ArtistResponse> mockResponse = Response.success(artist);

        when(view.getArtistMbid()).thenReturn("mbid");
        when(api.getArtist("mbid")).thenReturn(call);
        presenter.getArtist();

        verify(view).showLoading();
        verify(call).enqueue(callbackCaptor.capture());

        callbackCaptor.getValue().onResponse(call, mockResponse);

        verify(view).setArtist(artist.getArtist());
        verify(view).showContent();
    }

    @Test
    public void testFailure() throws Exception {
        Throwable throwable = mock(Throwable.class);

        when(view.getArtistMbid()).thenReturn("mbid");
        when(api.getArtist("mbid")).thenReturn(call);
        presenter.getArtist();

        verify(call).enqueue(callbackCaptor.capture());

        callbackCaptor.getValue().onFailure(call, throwable);

        verify(view).showError(throwable);
    }
}