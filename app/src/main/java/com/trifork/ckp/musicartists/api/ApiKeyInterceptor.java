package com.trifork.ckp.musicartists.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Adds an api_key query parameter to every request.
 */
public final class ApiKeyInterceptor implements Interceptor {

    private final String lastFmApiKey;

    public ApiKeyInterceptor(String lastFmApiKey) {
        this.lastFmApiKey = lastFmApiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", lastFmApiKey)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
