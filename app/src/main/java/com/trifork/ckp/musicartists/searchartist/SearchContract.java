package com.trifork.ckp.musicartists.searchartist;

import com.trifork.ckp.musicartists.model.ArtistListItem;

import java.util.List;

public interface SearchContract {
    interface SearchArtistView {
        String searchInput();
        void showResultList(List<ArtistListItem> artists);
    }
    interface SearchPresenter {
        void searchArtist();
    }
}
