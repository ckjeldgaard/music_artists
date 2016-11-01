package com.trifork.ckp.musicartists.model;

public class Bio {

    private final String summary;
    private final String content;

    public Bio(String summary, String content) {
        this.summary = summary;
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }
}
