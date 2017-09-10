package com.shoppingapp.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.R;
import com.shoppingapp.PagerAdapter.ShopPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class WatchlistFragment extends Fragment {
    TabLayout watchlistTabLayout;
    ViewPager watchlistViewPager;
    public WatchlistFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        watchlistTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        watchlistViewPager = (ViewPager) view.findViewById(R.id.pager);
//       tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ternds)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.feeds)));

       // whishlistTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        watchlistTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ShopPagerAdapter adapter = new ShopPagerAdapter(getActivity().getSupportFragmentManager());
        watchlistViewPager.setAdapter(adapter);
        watchlistTabLayout.setupWithViewPager(watchlistViewPager);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        watchlistTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                watchlistViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view ;
    }

}
