package com.trifork.ckp.musicartists.searchartist;


import com.trifork.ckp.musicartists.model.Artist;

import java.util.List;

public interface SearchContract {
    interface SearchArtistView {
        String searchInput();
        void showResultList(List<Artist> artists);
    }
    interface SearchArtistUserActions {
        void searchArtist(String artist);
    }
}
