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

public class ItemDetailsFragment extends Fragment {

    public ItemDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        return  view;
    }




}
