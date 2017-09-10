package com.shoppingapp.PagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shoppingapp.Fragment.ShowItemFragment;
import com.shoppingapp.interfaces.Constant;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class ShopPagerAdapter extends FragmentStatePagerAdapter {


    final  int COUNT =6;

    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = new ShowItemFragment();
        Bundle bundle = new Bundle();
        switch (position){
            case Constant.FRAGMENT_ONE:
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_TWO:
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_THREE :
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_FOUR :
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_FIVE :
                bundle.putString("url", Constant.url + "?action=item&category=1");
                break;
            case Constant.FRAGMENT_SIX :
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
        String title ="";
        switch (position){
            case 0 :
                return "Clothing";
            case 1 :
                return "Jeans";
            case 2 :
                return "Shoes";
            case 3 :
                return "Watches";
            case 4 :
                return "Bags";
            case 5 :
                return "Accessories";


        }

        return title;
    }
}
