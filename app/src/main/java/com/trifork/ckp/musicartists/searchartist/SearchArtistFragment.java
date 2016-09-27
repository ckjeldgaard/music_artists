package com.trifork.ckp.musicartists.searchartist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.api.LastFmClient;
import com.trifork.ckp.musicartists.model.ArtistListItem;
import com.trifork.ckp.musicartists.searchartist.list.ArtistItemListener;
import com.trifork.ckp.musicartists.searchartist.list.ArtistListPicassoImage;
import com.trifork.ckp.musicartists.searchartist.list.ArtistsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArtistFragment extends Fragment implements Callback<List<ArtistListItem>>, ArtistItemListener {

    public static final String TAG = SearchArtistFragment.class.getSimpleName();

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

        List<ArtistListItem> items = new ArrayList<>();
        //items.add(new ArtistListItem("Metallica", "", ""));
        //items.add(new ArtistListItem("Mastodon", "", ""));
        adapter = new ArtistsAdapter(items, this, new ArtistListPicassoImage(getContext()));

        Call<List<ArtistListItem>> call = new LastFmClient().getServiceApi().searchArtist("gojira");
        call.enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_artist, container, false);

        artistsRecyclerView = (RecyclerView) root.findViewById(R.id.list_artists);
        artistsRecyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onArtistClick(ArtistListItem clickedArtist) {
        Log.d(TAG, "onArtistClick() called with: clickedArtist = [" + clickedArtist + "]");
    }

    @Override
    public void onResponse(Call<List<ArtistListItem>> call, Response<List<ArtistListItem>> response) {
        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

        Log.d(TAG, "artists = " + response.body());
        Log.d(TAG, "url = " + call.request().url().toString());

        for (ArtistListItem artist : response.body()) {
            Log.d(TAG, "artist = " + artist);
        }

        adapter = new ArtistsAdapter(response.body(), this, new ArtistListPicassoImage(getContext()));
        artistsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<ArtistListItem>> call, Throwable t) {
        Log.e(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
        t.printStackTrace();
    }
}
