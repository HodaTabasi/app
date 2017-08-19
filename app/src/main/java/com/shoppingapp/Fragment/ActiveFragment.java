package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import na.com.rawnaq.R;
import na.com.rawnaq.adapter.OrderAdapter;
import na.com.rawnaq.model.Order;

/**
  Created by DevAmar on 8/10/17.
 */

public class ActiveFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private OrderAdapter mOrderAdapter;
    private ArrayList<Order> orders  = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_active , container , false);

        initView();

        setUpRecyclerView();

        fetchAllOrders();

        return mView;
    }



    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv);
    }

    private void setUpRecyclerView() {
        mOrderAdapter = new OrderAdapter(orders , getActivity());
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManger);
        mRecyclerView.setAdapter(mOrderAdapter);
    }

    private void fetchAllOrders() {
        orders.add(new Order(123 , "123457","11.4.2017",getString(R.string.processed),"11.3.2019"));
        orders.add(new Order(123 , "123457","11.4.2017",getString(R.string.order_delivered),"11.3.2019"));
        orders.add(new Order(123 , "123457","11.4.2017",getString(R.string.order_on_delivery),"11.3.2019"));
        mOrderAdapter.notifyDataSetChanged();
    }

}
