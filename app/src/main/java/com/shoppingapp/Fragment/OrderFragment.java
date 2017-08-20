package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.Adapter.OrderViewPagerAdapter;
import com.shoppingapp.R;

/**
  Created by DevAmar on 8/15/17.
 */

public class OrderFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private View mView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order , container , false);

        initView();

        setUpViewPager();

        return mView;
    }




    private void initView() {
        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) mView.findViewById(R.id.tab_layout);
    }

    private void setUpViewPager() {
        OrderViewPagerAdapter orderViewPagerAdapter = new OrderViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(orderViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
