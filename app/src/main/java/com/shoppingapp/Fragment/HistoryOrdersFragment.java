package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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
        historyOrders.add(new Order(123 , "123457","11.4.2017",getString(R.string.processed),"11.3.2019"));
        historyOrders.add(new Order(123 , "123457","11.4.2017",getString(R.string.order_delivered),"11.3.2019"));
        historyOrders.add(new Order(123 , "123457","11.4.2017",getString(R.string.order_on_delivery),"11.3.2019"));
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        historyOrdersRecycler.setLayoutManager(linearLayoutManger);
        orderAdapter = new OrderAdapter(  getActivity(),historyOrders);
        historyOrdersRecycler.setAdapter(orderAdapter);



        return mView;
    }


}
