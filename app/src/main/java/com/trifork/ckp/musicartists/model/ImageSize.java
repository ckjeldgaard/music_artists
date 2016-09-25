package com.trifork.ckp.musicartists.model;

import com.google.gson.annotations.SerializedName;

public enum ImageSize {
    @SerializedName("small")
    SMALL("small"),

    @SerializedName("medium")
    MEDIUM("medium"),

    @SerializedName("large")
    LARGE("large"),

    @SerializedName("extralarge")
    EXTRA_LARGE("extralarge"),

    @SerializedName("mega")
    MEGA("mega");

    private final String value;

    ImageSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
