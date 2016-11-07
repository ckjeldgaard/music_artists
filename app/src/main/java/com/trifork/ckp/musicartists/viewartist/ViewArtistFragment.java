package com.trifork.ckp.musicartists.viewartist;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.musicartists.MusicArtistsApplication;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.injection.DaggerViewArtistComponent;
import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.ImageSize;
import com.trifork.ckp.musicartists.searchartist.list.ArtistImage;

import javax.inject.Inject;

public class ViewArtistFragment extends Fragment implements ViewArtistContract.ViewArtistView {

    public static final String TAG = ViewArtistFragment.class.getSimpleName();
    private static final String MBID = "mbid";

    @Inject
    ViewArtistContract.ArtistPresenter presenter;

    private ArtistImage image = new DisplayArtistPicassoImage();

    private RelativeLayout contentView;
    private View loadingView;
    private View errorView;

    private ImageView artistImage;
    private TextView bioSummary;
    private TextView bioContent;

    private String mbid;

    public ViewArtistFragment() {
        // Required empty public constructor
    }

    public static ViewArtistFragment newInstance(String mbid) {

        ViewArtistFragment fragment = new ViewArtistFragment();
        Bundle args = new Bundle();
        args.putString(MBID, mbid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MusicArtistsApplication app = (MusicArtistsApplication) getActivity().getApplication();
        DaggerViewArtistComponent.builder()
                .viewArtistModule(app.getViewArtistModule(this))
                .build().inject(this);

        if (getArguments() != null) {
            mbid = getArguments().getString(MBID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_artist, container, false);

        loadingView = root.findViewById(R.id.loadingView);
        errorView = root.findViewById(R.id.errorView);

        contentView = (RelativeLayout) root.findViewById(R.id.content);

        artistImage = (ImageView) root.findViewById(R.id.artist_image);
        bioSummary = (TextView) root.findViewById(R.id.bio_summary);
        bioContent = (TextView) root.findViewById(R.id.bio_content);

        this.presenter.getArtist();

        return root;
    }

    @Override
    public String getArtistMbid() {
        return this.mbid;
    }

    @Override
    public void setArtist(Artist artist) {
        this.image.loadImage(
                getContext(),
                artist.getImage(ImageSize.EXTRA_LARGE).getUrl(),
                artistImage
        );
        this.bioSummary.setText(artist.getBio().getSummary());
        this.bioContent.setText(artist.getBio().getContent());
    }

    @Override
    public void showLoading() {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
