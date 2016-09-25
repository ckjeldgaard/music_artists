package com.trifork.ckp.musicartists.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public final class SearchResponse {

    @SerializedName("results") private final JsonObject results;

    public SearchResponse(JsonObject results) {
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
