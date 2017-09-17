package com.shoppingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shoppingapp.Adapter.OrderAdapter;
import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.Model.Order;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class ActiveOrdersFragment  extends Fragment {
    private static final String TAG = ActiveOrdersFragment.class.getSimpleName();
    RecyclerView activeOrdersRecycler;
    OrderAdapter orderAdapter;
    List<Order> activeOrders;
    View mView;

    public ActiveOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_recycler, container, false);
        activeOrdersRecycler = mView.findViewById(R.id.recycler);
        activeOrders = new ArrayList<>();

        orderAdapter = new OrderAdapter(getActivity(), activeOrders);
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        activeOrdersRecycler.setLayoutManager(linearLayoutManger);
        activeOrdersRecycler.setAdapter(orderAdapter);
        getOrder();

        return mView;
    }

    private void getOrder() {
        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");

                    String accountKitId = account.getId();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", accountKitId);

                    MyRequests.getInstance().getData(Constant.GET_ORDER_URL, params, new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) throws JSONException {
                            Log.e("result",result);
                            JSONObject object = new JSONObject(result);
                            JSONArray array = object.getJSONArray("orders");

                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object1 = array.getJSONObject(i);
                                Order order = gson.fromJson(object1.toString(), Order.class);
                                activeOrders.add(order);
                                Log.e("ssss",activeOrders.size()+"");
                            }
                            orderAdapter.notifyDataSetChanged();
                        }
                    });


                }

                @Override
                public void onError(final AccountKitError error) {
                    Toast.makeText(getApplicationContext(), "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();
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



}