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
 * Created by Yasmeen on 11/08/2017.
 */

public class ShopeFragment extends Fragment {
    TabLayout shopTabLayout ;
    ViewPager shopViewPager;
    public ShopeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        shopTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        shopViewPager = (ViewPager) view.findViewById(R.id.pager);
//       tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ternds)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.feeds)));

        shopTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ShopPagerAdapter adapter = new ShopPagerAdapter(getActivity().getSupportFragmentManager());
        shopViewPager.setAdapter(adapter);
        shopTabLayout.setupWithViewPager(shopViewPager);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        shopTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                shopViewPager.setCurrentItem(tab.getPosition());

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
