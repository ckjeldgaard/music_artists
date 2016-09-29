package com.trifork.ckp.musicartists.searchartist;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.TestApplication;
import com.trifork.ckp.musicartists.injection.SearchArtistModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class SearchArtistFragmentTest {

    private TestApplication app;

    @Before
    public void setUp() throws Exception {
        app = (TestApplication) RuntimeEnvironment.application;
    }

    @Test
    public void testSearchArtist() throws Exception {

        SearchArtistModule module = mock(SearchArtistModule.class);
        SearchContract.SearchPresenter presenter = mock(SearchContract.SearchPresenter.class);

        when(module.providePresenter()).thenReturn(presenter);
        app.setSearchArtistModule(module);

        SearchArtistFragment searchArtistFragment = new SearchArtistFragment();
        startFragment(searchArtistFragment);

        EditText searchField = (EditText) searchArtistFragment.getView().findViewById(R.id.search_artist_text);

        searchField.setText("band");
        verify(presenter).searchArtist();
    }
}