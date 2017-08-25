package com.shoppingapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shoppingapp.Dialog.DeliveryDialog;
import com.shoppingapp.R;

/**
 * Created by Yasmeen on 21/08/2017.
 */

public class CheckoutActivity extends AppCompatActivity {
    Button pay ;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setTitle(R.string.chechout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        pay = (Button) findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeliveryDialog deliveryDialog = new DeliveryDialog(getContext());
//                deliveryDialog.show();
            }
        });
    }


}
