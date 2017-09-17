package com.shoppingapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.Activity.MainActivity;
import com.shoppingapp.Dialog.EditProfileDialog;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.ProfileDate;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by Yasmeen on 23/08/2017.
 */

public class EditProfileFragment extends Fragment implements View.OnClickListener{
    private View mView;
    private ProgressBar mProgressBar;
    TextView phone_number, user_names, address, email;
    FloatingActionButton editprofile;
    ProfileDate profileDate;
    RelativeLayout rlo;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        initView();

        componentContent();

        return mView;
    }

    public void initView() {
        mProgressBar = mView.findViewById(R.id.pb);
        mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        rlo = mView.findViewById(R.id.rlo);
        phone_number = mView.findViewById(R.id.phone);
        user_names = mView.findViewById(R.id.user_names);
        address = mView.findViewById(R.id.address);
        email = mView.findViewById(R.id.email);
        editprofile = mView.findViewById(R.id.editprofile);
        editprofile.setOnClickListener(this);
    }

    public void componentContent() {
        if (AccountKit.getCurrentAccessToken() != null) {
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
                            user_names.setText(object1.getString("name"));
                            email.setText(object1.getString("email"));
                            address.setText(object1.getString("address"));
                            mProgressBar.setVisibility(View.INVISIBLE);
                            rlo.setVisibility(View.VISIBLE);


                        }
                    });
                    // Get phone number
                    PhoneNumber phoneNumber = account.getPhoneNumber();
                    String phoneNumberString = phoneNumber.toString();
                    phone_number.setText(phoneNumberString);
                }

                @Override
                public void onError(final AccountKitError error) {
                }
            });
        } else {
            verifyMobileNumber();
        }
    }

    private void verifyMobileNumber() {
        final Intent intent = new Intent(getContext(), AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN);
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);
        startActivityForResult(intent, Constant.APP_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.APP_REQUEST_CODE) { // confirm that this response matches your request
            final AccountKitLoginResult loginResult = AccountKit.loginResultWithIntent(data);
            if (loginResult == null || loginResult.wasCancelled()) {
                Toast.makeText(getApplicationContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
            } else if (loginResult.getError() != null) {
                Toast.makeText(getApplicationContext(), loginResult.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                final AccessToken accessToken = loginResult.getAccessToken();

                if (accessToken != null) {
                    Log.e("Error", "Success:" + accessToken.getAccountId());
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            Log.e("Error", "User Info Successfully");

                            final String accountKitId = account.getId();
                            Map<String, String> paramid = new HashMap<String, String>();
                            paramid.put("user_id", accountKitId);
                            PhoneNumber phoneNumber = account.getPhoneNumber();
                            final String phoneNumberString = phoneNumber.toString();

                            try {
                                MyRequests.getInstance().addToDataBase(Constant.GET_USER_INFO_URL, paramid, new VolleyCallback() {
                                    @Override
                                    public void onSuccessResponse(String result) throws JSONException {
                                        JSONObject object = new JSONObject(result);
                                        JSONObject object1 = object.getJSONObject("user");
                                        String s = object1.getString("access_token");

                                        if (!s.equals(account.getId())) {

                                            Map<String, String> params = new HashMap<String, String>();

                                            params.put("mobile", phoneNumberString);
                                            params.put("access_token", accountKitId);

                                            try {
                                                MyRequests.getInstance().addToDataBase(Constant.ADD_USER_URL, params, new VolleyCallback() {
                                                    @Override
                                                    public void onSuccessResponse(String result) throws JSONException {
                                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                        Log.e("add_user", result);
                                                    }
                                                });

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(final AccountKitError error) {
                            Toast.makeText(getApplicationContext(), error.getUserFacingMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Unknown response type", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editprofile:
                EditProfileDialog editProfileDialog = new EditProfileDialog(getContext());
                Map<String,String> data = new HashMap<>();
                data.put("name",user_names.getText().toString());
                data.put("phone",phone_number.getText().toString());
                data.put("address",address.getText().toString());
                data.put("email",email.getText().toString());
                profileDate = editProfileDialog.getDataDialog();
                profileDate.getData(data);
                editProfileDialog.show();
                break;

        }

    }
}
