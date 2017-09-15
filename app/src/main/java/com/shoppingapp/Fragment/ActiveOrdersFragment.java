package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.Adapter.OrderAdapter;
import com.shoppingapp.Model.Order;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class ActiveOrdersFragment  extends Fragment {
    private static final String TAG = ActiveOrdersFragment.class.getSimpleName();
    RecyclerView  activeOrdersRecycler;
    OrderAdapter orderAdapter;
    List<Order> activeOrders;
    View mView;

    public ActiveOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mView = inflater.inflate(R.layout.fragment_order_recycler, container, false);
        activeOrdersRecycler =  mView.findViewById(R.id.recycler);
        activeOrders = new ArrayList<>();

        String url = "http://evlope.com/rawnaq/add_order.php";


        activeOrders.add(new Order(1234 , "123457","11.4.2017",getString(R.string.processed),"11.3.2019"));
        activeOrders.add(new Order(1234 , "123457","11.4.2017",getString(R.string.order_delivered),"11.3.2019"));
        activeOrders.add(new Order(1234 , "123457","11.4.2017",getString(R.string.order_on_delivery),"11.3.2019"));
//        Log.e(TAG,"status "+activeOrders.get(2).getStatus());
        orderAdapter = new OrderAdapter(  getActivity(),activeOrders);
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        activeOrdersRecycler.setLayoutManager(linearLayoutManger);
        activeOrdersRecycler.setAdapter(orderAdapter);


        return mView;
    }


}