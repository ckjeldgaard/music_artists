package com.trifork.ckp.musicartists.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public final class ApiResponse {

    @SerializedName("results") private final JsonObject results;

    public ApiResponse(JsonObject results) {
        this.results = results;
    }

    public JsonObject getResults() {
        return results;
    }

    /*private final SearchResult results;

    public RootSearchResponse(SearchResult results) {
        this.results = results;
    }

    public SearchResult getResults() {
        return results;
    }*/
}
