package com.trifork.ckp.musicartists;

import com.trifork.ckp.musicartists.injection.SearchArtistModule;
import com.trifork.ckp.musicartists.searchartist.SearchContract;

public class TestApplication extends MusicArtistsApplication {

    //private List<ArtistListItem> mockArtistList = new ArrayList<>();
    private SearchArtistModule mockSearchArtistModule;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setSearchArtistModule(SearchArtistModule module) {
        this.mockSearchArtistModule = module;
    }

    @Override
    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {
        return mockSearchArtistModule;
    }

    /*@Override
    public SearchArtistModule getSearchArtistModule(SearchContract.SearchArtistView view) {

        LastFmApi api = mock(LastFmApi.class);

        final Call<List<ArtistListItem>> mockCall = mock(Call.class);
        doAnswer(
                new Answer<List<ArtistListItem>>() {
                    @Override
                    public List<ArtistListItem> answer(InvocationOnMock invocation) throws Throwable {
                        Response<List<ArtistListItem>> res = Response.success(mockArtistList);
                        ((Callback)invocation.getArguments()[0]).onResponse(mockCall, res);
                        return null;
                    }
                }
        ).when(mockCall).enqueue(any(Callback.class));

        when(api.searchArtist(anyString())).thenReturn(mockCall);

        return new SearchArtistModule(view, api);
    }*/
}
