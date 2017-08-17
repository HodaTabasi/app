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
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shoppingapp.Fragment.ExploreFragment;
import com.shoppingapp.Fragment.OrdersFragment;
import com.shoppingapp.Fragment.SearchFragment;
import com.shoppingapp.Fragment.ShopeFragment;
import com.shoppingapp.Fragment.WhishlistFragment;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Toolbar toolbar;
    private CharSequence mTitle ;

    TextView actionTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionTitle = (TextView)findViewById(R.id.title);
       setSupportActionBar(toolbar);

//       FragmentManager mFragmentManager = getSupportFragmentManager();
//      FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.container,new AllCategoriesFragment()).commit();

        ExploreFragment fragment = new ExploreFragment();
      FragmentsUtil.addFragment(this,R.id.container,fragment,false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            mTitle = getString(R.string.shop);
            transaction.replace(R.id.container, new ShopeFragment());
            transaction.commit();

        }
        else if (id == R.id.nav_order) {
            mTitle = getString(R.string.orders);
            transaction.replace(R.id.container, new OrdersFragment());
             transaction.commit();

        }
        else if (id == R.id.nav_whishlist) {
            mTitle = getString(R.string.whishList);
            transaction.replace(R.id.container, new WhishlistFragment());
            transaction.commit();

        }
        else if (id == R.id.nav_explore) {
            mTitle = getString(R.string.explor);
            transaction.replace(R.id.container, new ExploreFragment());
            transaction.commit();
        }
        else if (id == R.id.nav_search) {
            mTitle = getString(R.string.search);
            transaction.replace(R.id.container, new SearchFragment());
            transaction.commit();
        }

        actionTitle.setText(mTitle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
