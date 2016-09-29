package com.trifork.ckp.musicartists.injection;

import com.trifork.ckp.musicartists.searchartist.SearchArtistFragment;

import dagger.Component;

@Component(modules = SearchArtistModule.class)
public interface SearchArtistComponent {
    void inject(SearchArtistFragment fragment);
}