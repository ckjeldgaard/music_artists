package com.trifork.ckp.musicartists.api;


import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.Image;
import com.trifork.ckp.musicartists.model.ImageSize;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArtistSearchJsonDeserializer implements JsonDeserializer<List<ArtistListItem>> {

    private static final String IMAGE_SIZE = "large";

    @Override
    public List<ArtistListItem> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<ArtistListItem> artistList = new ArrayList<>();

        JsonArray artists = json.getAsJsonObject()
                .getAsJsonObject("results")
                .getAsJsonObject("artistmatches")
                .getAsJsonArray("artist");

        for (int i = 0; i < artists.size(); i++) {
            JsonObject artist = artists.get(i).getAsJsonObject();
            String name = artist.get("name").getAsString();
            String url = artist.get("url").getAsString();
            String mbid = artist.get("mbid").getAsString();

            artistList.add(new ArtistListItem(name, mbid, url));
        }

        return artistList;
    }

    private Image fetchImage(JsonObject json) {
        Image image = null;
        JsonArray images = json.getAsJsonArray("image");
        for (int j = 0; j < images.size(); j++) {
            JsonObject jsonImage = images.get(j).getAsJsonObject();
            String size = jsonImage.get("size").getAsString();

            if (size.equals(IMAGE_SIZE)) {
                image = new Image(jsonImage.get("#text").getAsString(), ImageSize.LARGE);
            }
        }
        return image;
    }
}
