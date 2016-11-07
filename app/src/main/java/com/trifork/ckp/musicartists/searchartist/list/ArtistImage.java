package com.trifork.ckp.musicartists.searchartist.list;

import android.content.Context;
import android.widget.ImageView;

public interface ArtistImage {
    void loadImage(Context context, String source, ImageView target);
}
