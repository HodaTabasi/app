package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
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

public class HistoryOrdersFragment extends Fragment {

    private static final String TAG =HistoryOrdersFragment.class.getSimpleName() ;
    RecyclerView historyOrdersRecycler;
    OrderAdapter orderAdapter;
    List<Order> historyOrders;
    View mView;

    public HistoryOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_recycler, container, false);
        historyOrdersRecycler =  mView.findViewById(R.id.recycler);
        historyOrders = new ArrayList<>();
        historyOrders.add(new Order("1","delivered","11","2017-08-09","2017-08-10"));


        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        historyOrdersRecycler.setLayoutManager(linearLayoutManger);
        orderAdapter = new OrderAdapter(  getActivity(),historyOrders);
        historyOrdersRecycler.setAdapter(orderAdapter);



        return mView;
    }


}
