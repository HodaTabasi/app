package com.shoppingapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
   Button checkout ;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_cart, container, false);
        checkout = view.findViewById(R.id.chechout);
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
