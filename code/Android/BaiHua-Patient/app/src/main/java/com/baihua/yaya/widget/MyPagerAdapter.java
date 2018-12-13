package com.baihua.yaya.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        if (null == fragmentList)
            this.fragments = new ArrayList<>();
        else
            this.fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
