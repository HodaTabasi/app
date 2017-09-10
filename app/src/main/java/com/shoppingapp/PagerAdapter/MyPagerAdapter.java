package com.shoppingapp.PagerAdapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shoppingapp.Fragment.ShowItemFragment;
import com.shoppingapp.interfaces.Constant;

/**
 * Created by Yasmeen on 08/08/2017
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    final int COUNT = 2;
    private String[] titles = {"Trends","Feeds"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = new ShowItemFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case Constant.FRAGMENT_ONE:
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_TWO:
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
