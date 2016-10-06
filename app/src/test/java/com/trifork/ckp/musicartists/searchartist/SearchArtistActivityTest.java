package com.trifork.ckp.musicartists.searchartist;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.TestApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class SearchArtistActivityTest {
    @Test
    public void testFragmentAttachedToActivityOnStartup() throws Exception {
        SearchArtistActivity activity = Robolectric.setupActivity(SearchArtistActivity.class);

        assertThat("No fragments in SupportFragmentManager.", activity.getSupportFragmentManager().getFragments(), is(notNullValue()));
        assertThat(
                "SearchArtistFragment not attached to activity.",
                activity.getSupportFragmentManager().getFragments().get(0),
                is(instanceOf(SearchArtistFragment.class))
        );
    }

}