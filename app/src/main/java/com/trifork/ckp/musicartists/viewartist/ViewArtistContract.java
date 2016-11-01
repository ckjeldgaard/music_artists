package com.trifork.ckp.musicartists.viewartist;

import com.trifork.ckp.musicartists.MvpView;
import com.trifork.ckp.musicartists.model.Artist;

public interface ViewArtistContract {
    interface ViewArtistView extends MvpView {
        String getArtistMbid();
        void setArtist(Artist artist);
    }
    interface ArtistPresenter {
        void getArtist();
    }
}
