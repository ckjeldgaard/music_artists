package com.trifork.ckp.musicartists.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArtistListItemTest {

    private static final ArtistListItem artist1 = new ArtistListItem(
            "Artist name",
            "mbid",
            "url"
    );
    private static final ArtistListItem artist2 = new ArtistListItem(
            "Artist name",
            "mbid",
            "url"
    );

    @Test
    public void testEquals() throws Exception {
        assertThat(artist1.equals(artist2), is(true));
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(artist1.hashCode(), is(artist2.hashCode()));
    }

}