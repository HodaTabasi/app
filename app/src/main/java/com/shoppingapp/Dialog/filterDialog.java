package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;

import com.shoppingapp.R;

/**
 * Created by M.S.I on 8/17/2017
 */

public class filterDialog extends Dialog {
    Context context;
    public filterDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filter_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
