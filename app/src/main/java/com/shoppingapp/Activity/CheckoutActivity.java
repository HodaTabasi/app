package com.shoppingapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
Toolbar d_toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        d_toolbar = (Toolbar) findViewById(R.id.d_toolbar);
        d_toolbar.setTitle(R.string.chechout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setSupportActionBar(d_toolbar);
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
