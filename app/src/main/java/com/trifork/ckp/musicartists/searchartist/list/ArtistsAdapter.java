package com.trifork.ckp.musicartists.searchartist.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.model.ImageSize;

import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

    private final List<ArtistListItem> items;
    private final ArtistItemListener itemListener;
    private final ArtistImage artistImage;

    public ArtistsAdapter(List<ArtistListItem> items, ArtistItemListener itemListener, ArtistImage artistImage) {
        this.items = items;
        this.itemListener = itemListener;
        this.artistImage = artistImage;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_artistitem, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        final ArtistListItem artist = items.get(position);

        String url = artist.getImage(ImageSize.MEDIUM).getUrl();
        if (url.length() > 0) {
            artistImage.loadImage(
                    artist.getImage(ImageSize.MEDIUM).getUrl(),
                    holder.getImageView()
            );
        }
        holder.getTextView().setText(artist.getName());

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onArtistClick(artist);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
