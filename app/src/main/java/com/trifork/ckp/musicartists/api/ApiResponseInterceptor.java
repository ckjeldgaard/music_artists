package com.trifork.ckp.musicartists.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trifork.ckp.musicartists.model.ApiResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiResponseInterceptor implements Interceptor {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final Gson GSON = new Gson();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final ResponseBody body = response.body();
        ApiResponse apiResponse = GSON.fromJson(body.string(), ApiResponse.class);
        body.close();

        JsonArray artists = apiResponse.getResults()
                .getAsJsonObject("artistmatches")
                .getAsJsonArray("artist");

        final Response.Builder newResponse = response.newBuilder()
                .body(ResponseBody.create(JSON, artists.toString()));
        return newResponse.build();
    }
}
