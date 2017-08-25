package com.shoppingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class WatchesFragment extends Fragment {
    RecyclerView watchesView ;
    ItemAdapter itemAdapter ;
    List<ItemDetails> itemDetailsList;




    public WatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        watchesView = view.findViewById(R.id.trends_recycler);
        itemDetailsList = new ArrayList<>();
        itemDetailsList.add(new ItemDetails("1","Watch","100",R.drawable.watch));
        itemDetailsList.add(new ItemDetails("2","Watch","50",R.drawable.watch));
        itemDetailsList.add(new ItemDetails("3","Watch","300",R.drawable.watch));
        itemDetailsList.add(new ItemDetails("4","Watch","200",R.drawable.watch));
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2 );
        watchesView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(getActivity(),itemDetailsList);
        watchesView.setAdapter(itemAdapter);
        watchesView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),ItemDetailsActivity.class);
                startActivity(intent);
            }
        }));

        return  view;
    }
}
