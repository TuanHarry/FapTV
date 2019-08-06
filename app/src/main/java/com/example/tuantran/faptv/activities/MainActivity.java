package com.example.tuantran.faptv.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.tuantran.faptv.R;
import com.example.tuantran.faptv.adapters.TuanTranViewPagerAdapter;
import com.example.tuantran.faptv.fragments.ChannelInfoFragment;
import com.example.tuantran.faptv.fragments.playlist.FragmentPlaylist;
import com.example.tuantran.faptv.fragments.favorites.VideoFavoriteFragment;
import com.example.tuantran.faptv.utils.TuanTranViewpager;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.ncapdevi.fragnav.FragNavController;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    // tab
    private String[] tabsName;
    private TabLayout tabLayout;

    // view paper
    private ViewPager viewPager;
    private TuanTranViewPagerAdapter pagerAdapter;

    // searchview
    private MaterialSearchView searchView;
    private View searchViewOverlay;
    private String[] searchSuggestions;

    public FragNavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // tab indicator va viewpaper
        tabsName = new String[] {
                getResources().getString(R.string.tab_playlist),
                getResources().getString(R.string.tab_video),
                getResources().getString(R.string.tab_info)
        };
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (TuanTranViewpager) findViewById(R.id.viewpager);

       // FragmentManager fragment = getSupportFragmentManager();


        pagerAdapter = new TuanTranViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FragmentPlaylist.newInstances(), tabsName[0]);
        pagerAdapter.addFragment(VideoFavoriteFragment.newInstances(), tabsName[1]);
        pagerAdapter.addFragment(ChannelInfoFragment.newInstances(), tabsName[2]);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(tabsName.length);

        tabLayout.setupWithViewPager(viewPager);

        searchSuggestions = new String[]{};
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchViewOverlay = searchView.findViewById(R.id.transparent_view);
        searchView.setOnSearchViewListener(new SearchListener());
        searchView.setOnQueryTextListener(new SearchQueryListener());
        searchView.setSuggestions(searchSuggestions);
        searchView.setHint(getResources().getString(R.string.text_search_hint));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class SearchListener implements MaterialSearchView.SearchViewListener {
        @Override
        public void onSearchViewShown() {
            searchViewOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        public void onSearchViewClosed() {

        }
    }

    private class SearchQueryListener implements MaterialSearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


} // end Activity




