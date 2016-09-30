package com.trifork.ckp.musicartists.searchartist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.musicartists.R;

public final class ArtistViewHolder extends RecyclerView.ViewHolder {
    private final View itemView;
    private final ImageView imageArtist;
    private final TextView textArtist;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.imageArtist = (ImageView) itemView.findViewById(R.id.image);
        this.textArtist= (TextView) itemView.findViewById(R.id.content);
    }

    public View getItemView() {
        return itemView;
    }

    public ImageView getImageView() {
        return imageArtist;
    }

    public TextView getTextView() {
        return textArtist;
    }
}