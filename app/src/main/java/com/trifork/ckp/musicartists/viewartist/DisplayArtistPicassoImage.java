package com.trifork.ckp.musicartists.viewartist;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.searchartist.list.ArtistImage;

public class DisplayArtistPicassoImage implements ArtistImage {

    @Override
    public void loadImage(Context context, String source, ImageView target) {
        int width = Math.round(context.getResources().getDisplayMetrics().widthPixels);
        int height = Math.round(context.getResources().getDimension(R.dimen.image_artist_height));
        picassoLoad(context, source, target, width, height);
    }
    private void picassoLoad(Context context, String source, ImageView target, int width, int height) {
        Picasso.with(context)
                .load(source)
                .resize(width, height)
                .centerCrop()
                .into(target);
    }

}
