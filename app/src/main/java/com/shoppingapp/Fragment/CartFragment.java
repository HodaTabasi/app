package com.shoppingapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shoppingapp.Adapter.ItemCartAdapter;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
   Button checkout ;
    RecyclerView cartView ;
    ItemCartAdapter itemCartAdapter ;
    List<ItemCart> itemCartList;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_cart, container, false);
        checkout = view.findViewById(R.id.chechout);
        cartView = view.findViewById(R.id.cart_recycler);
        itemCartList = new ArrayList<>();
        itemCartList.add(new ItemCart("1","dress","200","1",R.drawable.dress));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        cartView.setLayoutManager(manager);
        itemCartAdapter = new ItemCartAdapter(getActivity(),itemCartList);
        cartView.setAdapter(itemCartAdapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutFragment fragment = new CheckoutFragment();
                FragmentsUtil.replaceFragment(getActivity(),R.id.container,fragment,true);
            }
        });

        return view;
    }

}
