package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.shoppingapp.Activity.MainActivity;
import com.shoppingapp.Fragment.EditProfileFragment;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.MyInterFace;
import com.shoppingapp.interfaces.ProfileDate;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by M.S.I on 9/15/2017.
 */

public class EditProfileDialog extends Dialog implements View.OnClickListener{
    Context context;
    Button saveDet;
    EditText edit_name,edit_email,edit_mobile,edit_address;
    Map<String,String> stringMap;


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
        putData();

    }

    private void initView() {
        Log.e("ffff","Dsfsd");
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_address = findViewById(R.id.edit_address);
        saveDet = findViewById(R.id.saveDet);
        saveDet.setOnClickListener(this);
    }

    private void putData() {
        edit_name.setText(stringMap.get("name").toString());
        edit_address.setText(stringMap.get("address").toString());
        edit_mobile.setText(stringMap.get("phone").toString());
        edit_email.setText(stringMap.get("email").toString());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveDet:

                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                    @Override
                    public void onSuccess(final Account account) {
                        Log.e("Error", "User Info Successfully");

                        final String accountKitId = account.getId();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_id", accountKitId);
                        params.put("name",edit_name.getText().toString());
                        params.put("email",edit_email.getText().toString());
                        params.put("address",edit_address.getText().toString());

                            MyRequests.getInstance().getData(Constant.UPDATE_PROFILE_URL, params, new VolleyCallback() {
                                @Override
                                public void onSuccessResponse(String result) throws JSONException {
                                    JSONObject object = new JSONObject(result);
                                    Log.e("resultdw",result);
                                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                                    dismiss();
                                    FragmentsUtil.replaceFragment((FragmentActivity) context,R.id.container, new EditProfileFragment(),true);
                                }
                            });



                    }

                    @Override
                    public void onError(final AccountKitError error) {
                        Toast.makeText(getApplicationContext(), error.getUserFacingMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
               break;
        }
    }

    public ProfileDate getDataDialog(){
        return myInterFace;
    }

    ProfileDate myInterFace = new ProfileDate() {
        @Override
        public void getData(Map<String, String> map) {
            Log.e("map",map.toString());
            stringMap = map;

        }
    };


}
