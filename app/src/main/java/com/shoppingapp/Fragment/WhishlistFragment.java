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
public class WhishlistFragment extends Fragment {
    TabLayout whishlistTabLayout ;
    ViewPager whishlistViewPager;
    public WhishlistFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        whishlistTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        whishlistViewPager = (ViewPager) view.findViewById(R.id.pager);
//       tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ternds)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.feeds)));

        whishlistTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ShopPagerAdapter adapter = new ShopPagerAdapter(getActivity().getSupportFragmentManager());
        whishlistViewPager.setAdapter(adapter);
        whishlistTabLayout.setupWithViewPager(whishlistViewPager);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        whishlistTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                whishlistViewPager.setCurrentItem(tab.getPosition());

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
