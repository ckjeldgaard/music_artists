package com.trifork.ckp.musicartists.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class LastFmClient {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "4b3207e16b62f1ce0e50e2d6d0e6b391";

    public LastFmClient() {
    }

    public LastFmApi getServiceApi(boolean addResponseInterceptor) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(interceptorClient(addResponseInterceptor))
                .build();

        return retrofit.create(LastFmApi.class);
    }

    private OkHttpClient interceptorClient(boolean addResponseInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (addResponseInterceptor) {
            builder.addInterceptor(new ApiResponseInterceptor());
        }
        builder.addInterceptor(new ApiKeyInterceptor(API_KEY));

        return builder.build();
    }
}
