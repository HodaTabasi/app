package com.shoppingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Yasmeen on 03/08/2017.
 */

public class FragmentsUtil {
    public  static  void addFragment(FragmentActivity activity , int layout, Fragment fragment, boolean addToBackStack){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    public static  void  replaceFragment(FragmentActivity activity , int layout, Fragment fragment, boolean addToBackStack){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
    public static  void  removeFragment(FragmentActivity activity , Fragment fragment){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }
}
