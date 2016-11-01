package com.trifork.ckp.musicartists.viewartist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trifork.ckp.musicartists.R;
import com.trifork.ckp.musicartists.searchartist.SearchArtistFragment;

public class ViewArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String artistMbid = intent.getStringExtra(SearchArtistFragment.ARTIST_MBID);

        if (null == savedInstanceState) {
            initFragment(ViewArtistFragment.newInstance(artistMbid));
        }
    }

    private void initFragment(Fragment viewArtistFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, viewArtistFragment);
        transaction.commit();
    }
}
