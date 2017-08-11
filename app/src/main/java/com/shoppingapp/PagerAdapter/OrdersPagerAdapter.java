package com.shoppingapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shoppingapp.Fragment.ActiveOrdersFragment;
import com.shoppingapp.Fragment.FeedsFragment;
import com.shoppingapp.Fragment.HistoryOrdersFragment;
import com.shoppingapp.Fragment.TrendsFragment;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class OrdersPagerAdapter extends FragmentPagerAdapter {
    final  int FRAGMENT_ONE =0;
    final  int FRAGMENT_TWO =1;
    final  int COUNT =2;

    public OrdersPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case FRAGMENT_ONE :
                fragment=new ActiveOrdersFragment();
                break;
            case FRAGMENT_TWO :
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
        String title ="";
        switch (position){
            case 0 :
                return "Active";
            case 1 :
                return "History";

        }

        return title;
    }
}
