package com.shoppingapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yasmeen on 23/08/2017.
 */

public class EditProfileFragment extends Fragment implements Observer {
    private View mView;
    private ProgressBar mProgressBar;
    TextView phone_number, user_names, address, email;
    private String url;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
//        FloatingActionButton editProfile = mView.findViewById(R.id.editprofile);
//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.edit_profile_dialog);
//                dialog.setTitle(Window.FEATURE_NO_TITLE);
//                dialog.show();
//            }
//        });
        initView();

        componentContent();

        MyRequests.getInstance().addObserver(this);
        MyRequests.getInstance().getItem(url);
        return mView;
    }

    private void initView() {
        phone_number = mView.findViewById(R.id.phone_number);
        user_names = mView.findViewById(R.id.user_names);
        address = mView.findViewById(R.id.address);
        email = mView.findViewById(R.id.email);
    }

    private void componentContent() {
        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");

                    String accountKitId = account.getId();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id","1");


                    MyRequests.getInstance().getUserInfo(Constant.GET_USER_INFO_URL, params, new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) throws JSONException {
                            Log.e("result_epf",result);
                        }
                    });
                    // Get phone number
                    PhoneNumber phoneNumber = account.getPhoneNumber();
                    String phoneNumberString = phoneNumber.toString();
                    phone_number.setText(phoneNumberString);

                    // Get email
                    String emailString = account.getEmail();
                    email.setText(emailString);

                }

                @Override
                public void onError(final AccountKitError error) {
                }
            });
        } else {
//            verifyMobileNumber();
        }
    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
