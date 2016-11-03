package com.trifork.ckp.musicartists.api;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ApiKeyInterceptorTest {
    @Test
    public void testInterceptorAddsKeyToQueryParameter() throws Exception {
        ApiKeyInterceptor interceptor = new ApiKeyInterceptor("TEST_KEY");

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        String apiQueryParameter = okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/")).build())
                .execute()
                .request()
                .url()
                .queryParameter("api_key");

        mockWebServer.shutdown();

        assertThat(apiQueryParameter, is("TEST_KEY"));
    }

}