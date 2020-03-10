package com.example.system.christsong;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;

import dao.DbHelper;

public class MainIndex extends AppCompatActivity {

    TabLayout tabLayout;
    Toolbar mToolbar;
    ViewPager vp;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_index);
      //  getSupportActionBar().setTitle(getIntent().getStringExtra("actionbartitle"));
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        // String title=getResources().getString(R.string.subject);
        // String title2=getResources().getString(R.string.index);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new Subjective(), getResources().getString(R.string.subject));
        adapter.add(new Indexing(), getResources().getString(R.string.index));
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem mSearch = menu.findItem(R.id.action_search);
//
//        SearchView mSearchView = (SearchView) mSearch.getActionView();
//        mSearchView.setQueryHint("Search");
//
//
////        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                return false;
////            }
////
////           // @Override
////           // public boolean onQueryTextChange(String newText) {
////               // mAdapter.getFilter().filter(newText);
////               //return true;
////            //}
////        });
//
//        return super.onCreateOptionsMenu(menu);
//    }

}

