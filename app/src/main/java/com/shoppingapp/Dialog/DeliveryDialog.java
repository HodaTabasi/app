package com.shoppingapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.shoppingapp.Activity.CheckoutActivity;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.MyInterFace;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M.S.I on 8/23/2017
 */

public class DeliveryDialog extends Dialog {
    Context context;
    Button pay;
    String total = " ";
    TextView prices, price_plus, note;
    EditText dphone, daddress;
     int t;

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

        note = findViewById(R.id.note);
        daddress = findViewById(R.id.daddress);
        dphone = findViewById(R.id.dphone);
        prices = findViewById(R.id.prices);

        userData();

        prices.setText(total);
        price_plus = findViewById(R.id.price_plus);
        t = Integer.parseInt(total);
        t+=20;
        price_plus.setText(String.valueOf(t));
        pay = findViewById(R.id.pay_item);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Intent intent = new Intent(view.getContext(),CheckoutActivity.class);
                intent.putExtra("subtotal",t+"");
                getContext().startActivity(intent);
            }
        });


    }

    private void userData(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                Log.e("Error", "User Info Successfully");

                String accountKitId = account.getId();
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",accountKitId);
                Log.e("user_id",accountKitId);


                MyRequests.getInstance().getData(Constant.GET_USER_INFO_URL, params, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) throws JSONException {
                        Log.e("result_epf",result);
                        JSONObject object = new JSONObject(result);
                        JSONObject object1 = object.getJSONObject("user");
                        if(object1.getString("address").equals("")){
                            note.setVisibility(View.VISIBLE);
                        }else {
                            daddress.setText(object1.getString("address"));
                        }

                    }
                });
                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                String phoneNumberString = phoneNumber.toString();
                dphone.setText(phoneNumberString);

            }

            @Override
            public void onError(final AccountKitError error) {
            }
        });
    }

    public MyInterFace getDataDialog(){
        return myInterFace;
    }

    MyInterFace myInterFace = new MyInterFace(){

        @Override
        public void onItemSelected(String Value) {
            Log.e("valuess",Value);
            total = Value;
        }
    };

}
