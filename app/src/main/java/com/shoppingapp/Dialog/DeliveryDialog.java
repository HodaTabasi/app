package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.shoppingapp.R;

/**
 * Created by M.S.I on 8/23/2017.
 */

public class DeliveryDialog extends Dialog {
    Context context;
    public DeliveryDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delivery);
    }
}
