package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.shoppingapp.Activity.CheckoutActivity;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.R;

/**
 * Created by M.S.I on 8/23/2017.
 */

public class DeliveryDialog extends Dialog {
    Context context;
    Button pay;
    public DeliveryDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_delivery);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        pay = findViewById(R.id.pay_item);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent intent = new Intent(view.getContext(),CheckoutActivity.class);
                getContext().startActivity(intent);
            }
        });

    }
}
