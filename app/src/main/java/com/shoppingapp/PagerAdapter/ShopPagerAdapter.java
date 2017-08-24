package com.shoppingapp.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shoppingapp.Fragment.AccessoriesFragment;
import com.shoppingapp.Fragment.BagsFragment;
import com.shoppingapp.Fragment.ClothingFragment;
import com.shoppingapp.Fragment.JeansFragment;
import com.shoppingapp.Fragment.ShoesFragment;
import com.shoppingapp.Fragment.TrendsFragment;
import com.shoppingapp.Fragment.WatchesFragment;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class ShopPagerAdapter extends FragmentStatePagerAdapter {
    final  int FRAGMENT_ONE =0;
    final  int FRAGMENT_TWO =1;
    final  int FRAGMENT_THREE =2;
    final  int FRAGMENT_FOUR =3;
    final  int FRAGMENT_FIVE=4;
    final  int FRAGMENT_SIX=5;
    final  int COUNT =6;

    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case FRAGMENT_ONE :
                fragment=new ClothingFragment();
                break;
            case FRAGMENT_TWO :
                fragment = new JeansFragment();
                break;
            case FRAGMENT_THREE :
                fragment = new ShoesFragment();
                break;
            case FRAGMENT_FOUR :
                fragment = new WatchesFragment();
                break;
            case FRAGMENT_FIVE :
                fragment = new BagsFragment();
                break;
            case FRAGMENT_SIX :
                fragment = new AccessoriesFragment();
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
