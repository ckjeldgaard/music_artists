package com.trifork.ckp.musicartists.searchartist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trifork.ckp.musicartists.MusicArtistsApplication;
import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.injection.DaggerSearchArtistComponent;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.searchartist.list.ArtistItemListener;
import com.trifork.ckp.musicartists.searchartist.list.ArtistListPicassoImage;
import com.trifork.ckp.musicartists.searchartist.list.ArtistsAdapter;
import com.trifork.ckp.musicartists.viewartist.ViewArtistActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchArtistFragment extends Fragment implements SearchContract.SearchArtistView, ArtistItemListener {

    public static final String TAG = SearchArtistFragment.class.getSimpleName();
    public final static String ARTIST_MBID = "com.trifork.ckp.musicartists.ARTIST_MBID";

    @Inject
    SearchContract.SearchPresenter presenter;

    private View loadingView;
    private View errorView;

    private EditText searchArtistText;
    private RecyclerView artistsRecyclerView;
    private ArtistsAdapter adapter;

    public SearchArtistFragment() {
        // Required empty public constructor
    }

    public static SearchArtistFragment newInstance() {
        return new SearchArtistFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MusicArtistsApplication app = (MusicArtistsApplication) getActivity().getApplication();
        DaggerSearchArtistComponent.builder()
                .searchArtistModule(app.getSearchArtistModule(this))
                .build().inject(this);

        List<ArtistListItem> items = new ArrayList<>();
        adapter = new ArtistsAdapter(items, this, new ArtistListPicassoImage());

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_artist, container, false);

        loadingView = root.findViewById(R.id.loadingView);
        errorView = root.findViewById(R.id.errorView);

        searchArtistText = (EditText) root.findViewById(R.id.search_artist_text);
        artistsRecyclerView = (RecyclerView) root.findViewById(R.id.list_artists);

        searchArtistText.addTextChangedListener(textChangedListener());
        artistsRecyclerView.setAdapter(adapter);

        showContent();
        return root;
    }

    private TextWatcher textChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (searchArtistText.getText().length() > 0) {
                    presenter.searchArtist();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Override
    public void onArtistClick(ArtistListItem clickedArtist) {
        Intent intent = new Intent(getActivity(), ViewArtistActivity.class);
        intent.putExtra(ARTIST_MBID, clickedArtist.getMbid());
        startActivity(intent);
    }

    @Override
    public String searchInput() {
        return searchArtistText.getText().toString();
    }

    @Override
    public void showResultList(List<ArtistListItem> artists) {
        adapter.update(artists);
    }

    @Override
    public void showLoading() {
        artistsRecyclerView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        artistsRecyclerView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        artistsRecyclerView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
