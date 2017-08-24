package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.PagerAdapter.MyPagerAdapter;
import com.shoppingapp.R;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class SearchFragment extends Fragment {
    TabLayout searchTabLayout ;
    ViewPager searchViewPager;
    public SearchFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        searchTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        searchViewPager = (ViewPager) view.findViewById(R.id.pager);
//       tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ternds)));
//        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.feeds)));

        searchTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        searchViewPager.setAdapter(adapter);
        searchTabLayout.setupWithViewPager(searchViewPager);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        searchTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                searchViewPager.setCurrentItem(tab.getPosition());

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
