package com.trifork.ckp.musicartists.injection;

import com.trifork.ckp.musicartists.viewartist.ViewArtistFragment;

import dagger.Component;

@Component(modules = ViewArtistModule.class)
public interface ViewArtistComponent {
    void inject(ViewArtistFragment fragment);
}