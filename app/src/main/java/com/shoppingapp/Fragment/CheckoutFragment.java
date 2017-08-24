package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shoppingapp.Dialog.DeliveryDialog;
import com.shoppingapp.R;

/**
 * Created by Yasmeen on 21/08/2017.
 */

public class CheckoutFragment extends Fragment {
    Button pay ;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        pay = view.findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryDialog deliveryDialog = new DeliveryDialog(getContext());
                deliveryDialog.show();
            }
        });



        return view;
    }
}
