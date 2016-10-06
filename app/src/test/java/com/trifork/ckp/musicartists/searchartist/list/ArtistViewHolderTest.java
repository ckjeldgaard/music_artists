package com.trifork.ckp.musicartists.searchartist.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.TestApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class ArtistViewHolderTest {

    @Mock
    View itemView;

    @Mock
    ImageView imageView;

    @Mock
    TextView textArtist;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItemView() throws Exception {
        ArtistViewHolder viewHolder = new ArtistViewHolder(itemView);
        assertThat(viewHolder.getItemView(), is(itemView));
    }

    @Test
    public void getImageView() throws Exception {
        when(itemView.findViewById(R.id.image)).thenReturn(imageView);
        ArtistViewHolder viewHolder = new ArtistViewHolder(itemView);
        assertThat(viewHolder.getImageView(), is(imageView));
    }

    @Test
    public void getTextView() throws Exception {
        when(itemView.findViewById(R.id.content)).thenReturn(textArtist);
        ArtistViewHolder viewHolder = new ArtistViewHolder(itemView);
        assertThat(viewHolder.getTextView(), is(textArtist));
    }

}