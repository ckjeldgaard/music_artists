package com.trifork.ckp.musicartists.model;

import com.google.gson.JsonObject;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchResponseTest {
    @Test
    public void getResults() throws Exception {
        JsonObject results = new JsonObject();
        SearchResponse searchResponse = new SearchResponse(results);
        assertThat(searchResponse.getResults(), is(results));
    }

}