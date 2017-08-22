package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.PagerAdapter.OrdersPagerAdapter;
import com.shoppingapp.R;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class OrdersFragment extends Fragment {
    TabLayout ordersTabLayout ;
    ViewPager ordersViewPager;
    public OrdersFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        ordersTabLayout =  view.findViewById(R.id.tab_layout);
        ordersViewPager =  view.findViewById(R.id.pager);
//       tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ternds)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.feeds)));

        ordersTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        OrdersPagerAdapter adapter = new OrdersPagerAdapter(getActivity().getSupportFragmentManager());
        ordersViewPager.setAdapter(adapter);
        ordersTabLayout.setupWithViewPager(ordersViewPager);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        ordersTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                ordersViewPager.setCurrentItem(tab.getPosition());

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
