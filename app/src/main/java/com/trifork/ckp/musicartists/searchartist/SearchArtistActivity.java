package com.trifork.ckp.musicartists.searchartist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trifork.ckp.musicartists.R;

public class SearchArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            initFragment(SearchArtistFragment.newInstance());
        }
    }

    private void initFragment(Fragment searchArtistFragment) {
        // Add the SearchArtistFragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, searchArtistFragment);
        transaction.commit();
    }
}
