package com.trifork.ckp.musicartists.searchartist.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trifork.ckp.musicartists.R;

public class ArtistViewHolder extends RecyclerView.ViewHolder {
    private final View artistItemView;
    private final ImageView imageArtist;
    private final TextView textArtist;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        this.artistItemView = itemView;
        this.imageArtist = (ImageView) itemView.findViewById(R.id.image);
        this.textArtist= (TextView) itemView.findViewById(R.id.content);
    }

    public View getItemView() {
        return artistItemView;
    }

    public ImageView getImageView() {
        return imageArtist;
    }

    public TextView getTextView() {
        return textArtist;
    }
}
