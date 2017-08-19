package com.shoppingapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shoppingapp.Fragment.ActiveFragment;


/**
 Created by DevAmar on 8/15/17.
 */

public class OrderViewPagerAdapter extends FragmentStatePagerAdapter {
    public OrderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ActiveFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
