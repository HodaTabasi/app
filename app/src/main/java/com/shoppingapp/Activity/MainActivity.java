package com.shoppingapp.Activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingapp.Dialog.EditProfileDialog;
import com.shoppingapp.Dialog.filterDialog;
import com.shoppingapp.Fragment.CartFragment;
import com.shoppingapp.Fragment.EditProfileFragment;
import com.shoppingapp.Fragment.ExploreFragment;
import com.shoppingapp.Fragment.OrdersFragment;
import com.shoppingapp.Fragment.ShopFragment;
import com.shoppingapp.Fragment.WatchlistFragment;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Toolbar toolbar;
    private CharSequence mTitle = "HOME";
    ImageView filter;
    TextView actionTitle;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        setSupportActionBar(toolbar);

        FragmentsUtil.addFragment(this, R.id.container, new ExploreFragment(), false);

        setUpDrawer();

        initAnimation();


    }

    private void initComponent(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        filter = toolbar.findViewById(R.id.filter);
        actionTitle = (TextView) findViewById(R.id.title);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        filter.setOnClickListener(this);
    }

    private void initAnimation(){
        Slide slide = new Slide(GravityCompat.getAbsoluteGravity(Gravity.END,getResources().getConfiguration().getLayoutDirection()));
        slide.setDuration(getResources().getInteger(R.integer.MEDIUM_DURATION));
        getWindow().setEnterTransition(slide);
    }
    private void setUpDrawer(){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, navigationView, false);
        navigationView.addHeaderView(headerView);
        navigationView.setNavigationItemSelectedListener(this);

        addHeaderAction(headerView);

    }

    private void addHeaderAction(View headerView) {
        ImageView imageView = headerView.findViewById(R.id.user_profile_img);
        TextView user_edit_prof = headerView.findViewById(R.id.user_edit_prof);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                mTitle = "Profile";
                filter.setVisibility(View.GONE);
                transaction.replace(R.id.container, new EditProfileFragment());
                transaction.commit();

                drawer.closeDrawer(GravityCompat.START);
            }
        });

        user_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                mTitle = "Profile";
                filter.setVisibility(View.GONE);
                transaction.replace(R.id.container, new EditProfileFragment());
                transaction.commit();

                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            actionTitle.setText(mTitle);
            if (mTitle.equals(getString(R.string.shop)) || mTitle.equals("HOME"))
                filter.setVisibility(View.VISIBLE);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            mTitle = getString(R.string.shop);
            filter.setVisibility(View.VISIBLE);
            transaction.replace(R.id.container, new ShopFragment());
            transaction.commit();

        } else if (id == R.id.nav_order) {
            mTitle = getString(R.string.orders);
            filter.setVisibility(View.GONE);
            transaction.replace(R.id.container, new OrdersFragment());
            transaction.commit();

        } else if (id == R.id.nav_whishlist) {
            mTitle = getString(R.string.whishList);
            filter.setVisibility(View.GONE);
            transaction.replace(R.id.container, new WatchlistFragment());
            transaction.commit();

        } else if (id == R.id.nav_explore) {
            mTitle = getString(R.string.explor);
            filter.setVisibility(View.GONE);
            transaction.replace(R.id.container, new ExploreFragment());
            transaction.commit();
        } else if (id == R.id.nav_shopping_cart) {
            mTitle = getString(R.string.shoppingcart);
            filter.setVisibility(View.GONE);
            transaction.replace(R.id.container, new CartFragment());
            transaction.commit();
        }

        actionTitle.setText(mTitle);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setChange(String s) {
        actionTitle.setText(s);
        filter.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.filter:
                filterDialog filterDialog = new filterDialog(MainActivity.this);
                filterDialog.show();
            break;
        }
    }
}
