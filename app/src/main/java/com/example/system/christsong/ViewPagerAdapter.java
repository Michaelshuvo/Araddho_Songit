package com.example.system.christsong;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentlist=new ArrayList<>();
    private List<String> fragmentlisttitle=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlisttitle.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlisttitle.size();
    }
    public void add(Fragment fm,String title){
      fragmentlist.add(fm);
      fragmentlisttitle.add(title);

    }
}
