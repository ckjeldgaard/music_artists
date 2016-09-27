package com.trifork.ckp.musicartists.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchResponseInterceptorTest {

    @Test
    public void testInterceptReturnsNewResponse() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse().setBody(buildJsonRequest().toString()));

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new ApiResponseInterceptor()).build();
        Response response = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/")).build()).execute();

        assertThat(response.body().string(), is(expectedJsonResponse().toString()));
        mockWebServer.shutdown();
    }

    private JsonArray expectedJsonResponse() {
        JsonObject artist1 = new JsonObject();
        artist1.addProperty("name", "Artist name 1");
        artist1.addProperty("mbid", "123");
        JsonObject artist2 = new JsonObject();
        artist2.addProperty("name", "Artist name 2");
        artist2.addProperty("mbid", "234");
        JsonArray artists = new JsonArray();
        artists.add(artist1);
        artists.add(artist2);
        return artists;
    }

    private JsonObject buildJsonRequest() {
        JsonObject artist = new JsonObject();
        artist.add("artist", expectedJsonResponse());
        JsonObject artistMatches = new JsonObject();
        artistMatches.add("artistmatches", artist);
        JsonObject results = new JsonObject();
        results.add("results", artistMatches);
        return results;
    }
}