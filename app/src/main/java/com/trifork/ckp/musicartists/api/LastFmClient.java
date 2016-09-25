package com.trifork.ckp.musicartists.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class LastFmClient {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "4b3207e16b62f1ce0e50e2d6d0e6b391";

    public LastFmClient() {
    }

    public LastFmApi getServiceApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(interceptorClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(LastFmApi.class);
    }

    private OkHttpClient interceptorClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiResponseInterceptor())
                .build();
    }

    private OkHttpClient httpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }
}
