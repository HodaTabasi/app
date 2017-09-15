package com.shoppingapp.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.R;
import com.shoppingapp.PagerAdapter.ShopPagerAdapter;

/**
 * Created by Yasmeen on 11/08/2017
 */

public class ShopFragment extends Fragment {
    TabLayout shopTabLayout ;
    ViewPager shopViewPager;
    public ShopFragment() {
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

      //  shopTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        shopTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ShopPagerAdapter adapter = new ShopPagerAdapter(getActivity().getSupportFragmentManager(),false);
        shopViewPager.setAdapter(adapter);
        shopTabLayout.setupWithViewPager(shopViewPager);

        Drawable myDrawable = ContextCompat.getDrawable(getContext(), R.drawable.choth);
        myDrawable.setBounds(0,0,60,60);
        myDrawable.setTint(ContextCompat.getColor(getContext(),R.color.white));
        shopTabLayout.getTabAt(0).setIcon(myDrawable);

        Drawable myDrawable1 = ContextCompat.getDrawable(getContext(),R.drawable.jeans);
        myDrawable1.setBounds(0,0,60,60);
        myDrawable1.setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
        shopTabLayout.getTabAt(1).setIcon(myDrawable1);

        Drawable myDrawable2 = ContextCompat.getDrawable(getContext(),R.drawable.shoes);
        myDrawable2.setBounds(0,0,60,60);
        myDrawable2.setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
        shopTabLayout.getTabAt(2).setIcon(myDrawable2);

        Drawable myDrawable3 = ContextCompat.getDrawable(getContext(),R.drawable.watches);
        myDrawable3.setBounds(0,0,60,60);
        myDrawable3.setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
        shopTabLayout.getTabAt(3).setIcon(myDrawable3);

        Drawable myDrawable4 = ContextCompat.getDrawable(getContext(),R.drawable.bag);
        myDrawable4.setBounds(0,0,60,60);
        myDrawable4.setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
        shopTabLayout.getTabAt(4).setIcon(myDrawable4);

        Drawable myDrawable5 = ContextCompat.getDrawable(getContext(),R.drawable.accessories);
        myDrawable5.setBounds(0,0,60,60);
        myDrawable5.setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
        shopTabLayout.getTabAt(5).setIcon(myDrawable5);

        shopTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                shopViewPager.setCurrentItem(tab.getPosition());
                    tab.getIcon().setTint(ContextCompat.getColor(getContext(),R.color.white));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(ContextCompat.getColor(getContext(),R.color.lighter_white));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(ContextCompat.getColor(getContext(),R.color.white));
            }
        });

        return view ;
    }
}
