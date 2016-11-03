package com.trifork.ckp.musicartists.viewartist;


import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.TestApplication;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23, application = TestApplication.class)
@Ignore
public class ViewArtistFragmentTest {

    private TestApplication app;

    @Before
    public void setUp() throws Exception {
        app = (TestApplication) RuntimeEnvironment.application;
    }

    @Test
    public void testNewInstance() throws Exception {
        assertThat(
                ViewArtistFragment.newInstance(anyString()),
                is(instanceOf(ViewArtistFragment.class))
        );
    }

    @Test
    public void testGetArtist() throws Exception {
        ViewArtistFragment viewArtistFragment = new ViewArtistFragment();
        startFragment(viewArtistFragment);

        verify(app.getMockViewArtistPresenter()).getArtist();
    }
}