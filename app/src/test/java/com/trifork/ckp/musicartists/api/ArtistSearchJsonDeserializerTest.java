package com.trifork.ckp.musicartists.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.Image;
import com.trifork.ckp.musicartists.model.ImageSize;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ArtistSearchJsonDeserializerTest {

    private final static String JSON_SEARCH_RESPONSE = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"metallica\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"24993\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"30\",\"artistmatches\":{\"artist\":[{\"name\":\"Metallica\",\"listeners\":\"2697692\",\"mbid\":\"65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab\",\"url\":\"https://www.last.fm/music/Metallica\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/8a0202265ec642c6bcae358cbb05e9dd.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/8a0202265ec642c6bcae358cbb05e9dd.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/8a0202265ec642c6bcae358cbb05e9dd.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/8a0202265ec642c6bcae358cbb05e9dd.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/8a0202265ec642c6bcae358cbb05e9dd.png\",\"size\":\"mega\"}]},{\"name\":\"Lou Reed & Metallica\",\"listeners\":\"36597\",\"mbid\":\"9d1ebcfe-4c15-4d18-95d3-d919898638a1\",\"url\":\"https://www.last.fm/music/Lou+Reed+&+Metallica\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/4b17a235b2694901878d8c303eef462d.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/4b17a235b2694901878d8c303eef462d.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/4b17a235b2694901878d8c303eef462d.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/4b17a235b2694901878d8c303eef462d.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/4b17a235b2694901878d8c303eef462d.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"metallica\"}}}";

    @Test
    public void testArtistListDeserialization() throws Exception {
        JsonElement json = new Gson().fromJson(JSON_SEARCH_RESPONSE, JsonElement.class);

        List<ArtistListItem> expected = new ArrayList<ArtistListItem>(2) {{
            add(new ArtistListItem(
                    "Metallica",
                    "65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab",
                    "https://www.last.fm/music/Metallica"
            ));
            add(new ArtistListItem(
                    "Lou Reed & Metallica",
                    "9d1ebcfe-4c15-4d18-95d3-d919898638a1",
                    "https://www.last.fm/music/Lou+Reed+&+Metallica"
            ));
        }};

        ArtistSearchJsonDeserializer deserializer = new ArtistSearchJsonDeserializer();
        List<ArtistListItem> actual = deserializer.deserialize(json, null, null);
        assertThat(actual, is(expected));
    }
}