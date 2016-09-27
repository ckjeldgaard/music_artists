package com.trifork.ckp.musicartists.acceptance;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.searchartist.SearchArtistFragment;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
@Ignore
public class MusicArtistsAcceptanceTests {

    private EditText searchField;
    private RecyclerView listArtists;

    @Before
    public void setUp() throws Exception {
        SearchArtistFragment searchArtistFragment = new SearchArtistFragment();
        startFragment(searchArtistFragment);

        this.searchField = (EditText) searchArtistFragment.getView().findViewById(R.id.search_artist_text);
        this.listArtists = (RecyclerView) searchArtistFragment.getView().findViewById(R.id.list_artists);
    }

    @Test
    public void testSearchArtist() throws Exception {
        whenTheUserSearchFor("metallica");
        thenTheResultListShouldContain("Metallica");
    }

    /*@Test
    public void testDisplayArtistPage() throws Exception {
        givenTheUserSearchFor("metallica");
        whenTheUserSelectsListItem("Metallica");
        thenAnArtistPageDisplaysDetailsForArtist("Metallica");
    }*/

    private void whenTheUserSearchFor(String artist) {
        searchField.setText(artist);
    }

    private void thenTheResultListShouldContain(String artist) {
        assertThat(listArtists, is(notNullValue()));

        listArtists.measure(0, 0);
        listArtists.layout(0, 0, 100, 10000);

        ArrayList<String> actualList = new ArrayList<>();
        for (int i = 0; i < listArtists.getChildCount(); i++) {
            actualList.add(listArtists.findViewHolderForAdapterPosition(i).itemView.toString());
        }

        assertThat(actualList, contains(artist));
    }
}