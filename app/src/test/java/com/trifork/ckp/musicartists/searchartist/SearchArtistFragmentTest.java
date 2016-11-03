package com.trifork.ckp.musicartists.searchartist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.TestApplication;
import com.trifork.ckp.musicartists.model.ArtistListItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class SearchArtistFragmentTest {

    private TestApplication app;

    @Before
    public void setUp() throws Exception {
        app = (TestApplication) RuntimeEnvironment.application;
    }

    @Test
    public void testNewInstance() throws Exception {
        assertThat(SearchArtistFragment.newInstance(), is(instanceOf(SearchArtistFragment.class)));

    }

    @Test
    public void testSearchArtist() throws Exception {
        SearchArtistFragment searchArtistFragment = new SearchArtistFragment();
        startFragment(searchArtistFragment);

        EditText searchField = (EditText) searchArtistFragment.getView().findViewById(R.id.search_artist_text);

        searchField.setText("band");
        verify(app.mockSearchPresenter()).searchArtist();
    }

    @Test
    public void testSearchArtistNotInvokedForEmptySearch() throws Exception {
        SearchArtistFragment searchArtistFragment = new SearchArtistFragment();
        startFragment(searchArtistFragment);

        EditText searchField = (EditText) searchArtistFragment.getView().findViewById(R.id.search_artist_text);

        searchField.setText("");
        verify(app.mockSearchPresenter(), never()).searchArtist();
    }

    @Test
    public void testInvokeSearchInput() throws Exception {
        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);

        EditText searchField = (EditText) fragment.getView().findViewById(R.id.search_artist_text);
        searchField.setText("band");

        assertThat(fragment.searchInput(), is("band"));
    }

    @Test
    public void testShowSearchResult() throws Exception {
        ArtistListItem artist = mock(ArtistListItem.class);

        List<ArtistListItem> artists = new ArrayList<>();
        artists.add(artist);
        artists.add(artist);

        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);

        fragment.showResultList(artists);

        assertThat(artists.size(), is(recyclerView.getAdapter().getItemCount()));
    }

    @Test
    public void testShowContent() throws Exception {
        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);
        View loadingView = fragment.getView().findViewById(R.id.loadingView);
        View errorView = fragment.getView().findViewById(R.id.errorView);

        fragment.showContent();

        assertThat(recyclerView.getVisibility(), is(View.VISIBLE));
        assertThat(loadingView.getVisibility(), is(View.GONE));
        assertThat(errorView.getVisibility(), is(View.GONE));
    }

    @Test
    public void testShowLoading() throws Exception {
        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);
        View loadingView = fragment.getView().findViewById(R.id.loadingView);
        View errorView = fragment.getView().findViewById(R.id.errorView);

        fragment.showLoading();

        assertThat(recyclerView.getVisibility(), is(View.GONE));
        assertThat(loadingView.getVisibility(), is(View.VISIBLE));
        assertThat(errorView.getVisibility(), is(View.GONE));
    }

    @Test
    public void testShowError() throws Exception {
        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);
        View loadingView = fragment.getView().findViewById(R.id.loadingView);
        View errorView = fragment.getView().findViewById(R.id.errorView);

        fragment.showError(mock(Throwable.class));

        assertThat(recyclerView.getVisibility(), is(View.GONE));
        assertThat(loadingView.getVisibility(), is(View.GONE));
        assertThat(errorView.getVisibility(), is(View.VISIBLE));
    }
}