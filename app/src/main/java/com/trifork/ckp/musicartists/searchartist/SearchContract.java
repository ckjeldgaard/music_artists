package com.trifork.ckp.musicartists.searchartist;

import com.trifork.ckp.musicartists.MvpView;
import com.trifork.ckp.musicartists.model.ArtistListItem;

import java.util.List;

public interface SearchContract {
    interface SearchArtistView extends MvpView {
        String searchInput();
        void showResultList(List<ArtistListItem> artists);
    }
    interface SearchPresenter {
        void searchArtist();
    }
}
