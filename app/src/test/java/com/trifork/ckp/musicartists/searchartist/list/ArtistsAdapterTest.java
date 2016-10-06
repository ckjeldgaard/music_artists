package com.trifork.ckp.musicartists.searchartist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.musicartists.BuildConfig;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.TestApplication;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.Image;
import com.trifork.ckp.musicartists.searchartist.SearchArtistFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.trifork.ckp.musicartists.model.ImageSize.MEDIUM;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class ArtistsAdapterTest {

    @Mock
    ArtistItemListener listener;

    @Mock
    ArtistImage artistImage;

    @Mock
    ArtistViewHolder viewHolder;

    @Mock
    TextView textView;

    @Mock
    ImageView imageView;

    @Mock
    View itemView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewHolder.getItemView()).thenReturn(itemView);
        when(viewHolder.getImageView()).thenReturn(imageView);
        when(viewHolder.getTextView()).thenReturn(textView);
    }

    @Test
    public void testSettingArtistNameOnBind() throws Exception {
        List<ArtistListItem> artists = mockArtists();
        ArtistsAdapter adapter = new ArtistsAdapter(artists, listener, artistImage);

        adapter.onBindViewHolder(viewHolder, 0);

        verify(textView).setText(artists.get(0).getName());
    }

    @Test
    public void testSettingArtistImageOnBind() throws Exception {
        List<ArtistListItem> artists = mockArtists();
        ArtistsAdapter adapter = new ArtistsAdapter(artists, listener, artistImage);

        adapter.onBindViewHolder(viewHolder, 0);

        verify(artistImage).loadImage(artists.get(0).getImage(MEDIUM).getUrl(), imageView);
    }

    @Test
    public void testImageNotLoadedForEmptyImageUrl() throws Exception {
        List<ArtistListItem> artists = mockArtists();
        ArtistsAdapter adapter = new ArtistsAdapter(artists, listener, artistImage);

        adapter.onBindViewHolder(viewHolder, 0);

        verify(artistImage, never()).loadImage(artists.get(0).getImages().get(1).getUrl(), imageView);
    }

    @Test
    public void testNotifyListenerOnItemClick() throws Exception {
        List<ArtistListItem> artists = mockArtists();
        ArtistsAdapter adapter = new ArtistsAdapter(artists, listener, artistImage);

        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);
        ArtistViewHolder artistViewHolder = adapter.onCreateViewHolder(recyclerView, 0);
        adapter.onBindViewHolder(artistViewHolder, 0);

        artistViewHolder.getItemView().performClick();

        verify(listener).onArtistClick(artists.get(0));
    }

    @Test
    public void testOnCreateViewHolder() throws Exception {
        List<ArtistListItem> artists = mockArtists();
        ArtistsAdapter adapter = new ArtistsAdapter(artists, listener, artistImage);

        SearchArtistFragment fragment = new SearchArtistFragment();
        startFragment(fragment);
        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.list_artists);

        assertThat(
                adapter.onCreateViewHolder(recyclerView, 0),
                is(instanceOf(ArtistViewHolder.class))
        );
    }

    private List<ArtistListItem> mockArtists() {
        return new ArrayList<ArtistListItem>() {{
            add(new ArtistListItem("bandname", "mbid", "url", mockImages()));
        }};
    }

    private List<Image> mockImages() {
        return new ArrayList<Image>() {{
           add(new Image("imageUrl", MEDIUM));
           add(new Image("", MEDIUM));
        }};
    }

}