package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

public class TrendsFragment extends Fragment {
RecyclerView trendsView ;
    ItemAdapter itemAdapter ;
    List<ItemDetails> itemDetailsList;




    public TrendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        trendsView = view.findViewById(R.id.trends_recycler);
        itemDetailsList = new ArrayList<>();
        itemDetailsList.add(new ItemDetails("1","Watch","100",R.drawable.watch));
        itemDetailsList.add(new ItemDetails("1","Off shoulder dress black","100",R.drawable.dress2));
        itemDetailsList.add(new ItemDetails("1","Off shoulder dress grey","120",R.drawable.dress));
        itemDetailsList.add(new ItemDetails("1","Timberland Heritage","100",R.drawable.photo));
        itemDetailsList.add(new ItemDetails("1","Crazy horse leather bag","100",R.drawable.bag));
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2 );
trendsView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(getActivity(),itemDetailsList);
        trendsView.setAdapter(itemAdapter);
 trendsView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
     @Override
     public void onItemClick(View view, int position) {
         ItemDetailsFragment fragment = new ItemDetailsFragment();
         FragmentsUtil.replaceFragment(getActivity(),R.id.container,fragment,true);
     }
 }));

        return  view;
    }




}
