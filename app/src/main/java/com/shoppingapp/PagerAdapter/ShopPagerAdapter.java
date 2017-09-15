package com.shoppingapp.PagerAdapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shoppingapp.Fragment.ShowItemFragment;
import com.shoppingapp.interfaces.Constant;

/**
 * Created by Yasmeen on 11/08/2017
 */

public class ShopPagerAdapter extends FragmentStatePagerAdapter {

    final int COUNT = 6;
    private boolean favorite;

    public ShopPagerAdapter(FragmentManager fm, boolean favorite) {
        super(fm);
        this.favorite = favorite;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = new ShowItemFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case Constant.FRAGMENT_ONE:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=1");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 1);
                }
                break;
            case Constant.FRAGMENT_TWO:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=2");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 2);
                }
                break;
            case Constant.FRAGMENT_THREE:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=3");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 3);
                }
                break;
            case Constant.FRAGMENT_FOUR:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=4");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 4);
                }
                break;
            case Constant.FRAGMENT_FIVE:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=5");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 5);
                }
                break;
            case Constant.FRAGMENT_SIX:
                if (!favorite) {
                    bundle.putString("url", Constant.url + "?action=item&category=6");
                } else {
                    bundle.putString("url", Constant.GET_FAVORITE_URL);
                    bundle.putInt("type", 6);
                }
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }


}
