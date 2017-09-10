package com.shoppingapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.shoppingapp.Fragment.ActiveOrdersFragment;
import com.shoppingapp.Fragment.HistoryOrdersFragment;
import com.shoppingapp.interfaces.Constant;

/**
 * Created by Yasmeen on 11/08/2017
 */

public class OrdersPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = OrdersPagerAdapter.class.getSimpleName();
    private final int COUNT = 2;


    public OrdersPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.e("orderPagerAdapter", "hello");
    }

    @Override
    public Fragment getItem(int position) {
        Log.e(TAG + " position1", position + "  ");
        Fragment fragment = null;
        switch (position) {
            case Constant.FRAGMENT_ONE:
                Log.e(TAG, "FRAGMENT_ONE");
                fragment = new ActiveOrdersFragment();
                break;
            case Constant.FRAGMENT_TWO:
                Log.e(TAG, "FRAGMENT_TWO");
                fragment = new HistoryOrdersFragment();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case Constant.FRAGMENT_ONE:
                return "Active";
            case Constant.FRAGMENT_TWO:
                return "History";

        }

        return title;
    }
}
