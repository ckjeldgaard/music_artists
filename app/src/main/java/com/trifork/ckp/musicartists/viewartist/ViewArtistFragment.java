package com.trifork.ckp.musicartists.viewartist;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trifork.ckp.musicartists.MusicArtistsApplication;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.injection.DaggerViewArtistComponent;
import com.trifork.ckp.musicartists.model.Artist;
import com.trifork.ckp.musicartists.model.ImageSize;

import javax.inject.Inject;

public class ViewArtistFragment extends Fragment implements ViewArtistContract.ViewArtistView {

    public static final String TAG = ViewArtistFragment.class.getSimpleName();
    private static final String MBID = "mbid";

    @Inject
    ViewArtistContract.ArtistPresenter presenter;

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private NestedScrollView contentView;
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

        contentView = (NestedScrollView) root.findViewById(R.id.content);
        collapsingToolbar = (CollapsingToolbarLayout) root.findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);

        artistImage = (ImageView) root.findViewById(R.id.artist_image);
        bioSummary = (TextView) root.findViewById(R.id.bio_summary);
        bioContent = (TextView) root.findViewById(R.id.bio_content);

        collapsingToolbar.setTitle("Artist");
        toolbar.setTitle("Artist");

        this.presenter.getArtist();

        return root;
    }

    @Override
    public String getArtistMbid() {
        return this.mbid;
    }

    @Override
    public void setArtist(Artist artist) {
        collapsingToolbar.setTitle(artist.getName());
        toolbar.setTitle(artist.getName());

        bioSummary.setText(artist.getBio().getSummary());
        bioContent.setText(artist.getBio().getContent());

        Picasso.with(getContext())
                .load(artist.getImage(ImageSize.EXTRA_LARGE).getUrl())
                .into(artistImage);
    }

    @Override
    public void showLoading() {
        collapsingToolbar.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        collapsingToolbar.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        collapsingToolbar.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
