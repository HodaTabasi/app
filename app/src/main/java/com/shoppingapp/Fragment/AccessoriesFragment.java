package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class AccessoriesFragment extends Fragment {
    RecyclerView AccessoriesView ;
    ItemAdapter itemAdapter ;
    List<ItemDetails> itemDetailsList;




    public AccessoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        AccessoriesView = view.findViewById(R.id.trends_recycler);
        itemDetailsList = new ArrayList<>();
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2 );
        AccessoriesView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(getActivity(),itemDetailsList);
        AccessoriesView.setAdapter(itemAdapter);
        AccessoriesView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));

        return  view;
    }
}
