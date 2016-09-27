package com.trifork.ckp.musicartists.searchartist.list;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.musicartists.R;

public class ArtistListPicassoImage implements ArtistImage {

    private final Context context;

    public ArtistListPicassoImage(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String source, ImageView target) {
        int width = Math.round(context.getResources().getDimension(R.dimen.image_artist_list_width));
        int height = Math.round(context.getResources().getDimension(R.dimen.image_artist_list_height));
        picassoLoad(source, target, width, height);
    }

    private void picassoLoad(String source, ImageView target, int width, int height) {
        Picasso.with(context)
                .load(source)
                .placeholder(R.mipmap.node)
                .resize(width, height)
                .centerCrop()
                .into(target);
    }

}
