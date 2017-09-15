package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.shoppingapp.R;

/**
 * Created by M.S.I on 9/15/2017.
 */

public class EditProfileDialog extends Dialog implements View.OnClickListener{
    Context context;
    Button saveDet;

    public EditProfileDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_profile_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        initView();

    }

    private void initView() {
        saveDet = findViewById(R.id.saveDet);
        saveDet.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveDet:
                Toast.makeText(context, "dsfsdf", Toast.LENGTH_SHORT).show();
        }
    }
}
