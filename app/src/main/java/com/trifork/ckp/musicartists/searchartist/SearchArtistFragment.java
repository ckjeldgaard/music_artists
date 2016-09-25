package com.trifork.ckp.musicartists.searchartist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.api.LastFmClient;
import com.trifork.ckp.musicartists.model.ArtistListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArtistFragment extends Fragment implements Callback<List<ArtistListItem>>  {

    public static final String TAG = SearchArtistFragment.class.getSimpleName();

    public SearchArtistFragment() {
        // Required empty public constructor
    }

    public static SearchArtistFragment newInstance() {
        return new SearchArtistFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Call<List<ArtistListItem>> call = new LastFmClient().getServiceApi().searchArtist("mastodon");
        call.enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_artist, container, false);
    }

    @Override
    public void onResponse(Call<List<ArtistListItem>> call, Response<List<ArtistListItem>> response) {
        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

        Log.d(TAG, "artists = " + response.body());
        Log.d(TAG, "url = " + call.request().url().toString());

        for (ArtistListItem artist : response.body()) {
            Log.d(TAG, "artist = " + artist);
        }
    }

    @Override
    public void onFailure(Call<List<ArtistListItem>> call, Throwable t) {
        Log.e(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
        t.printStackTrace();
    }
}
