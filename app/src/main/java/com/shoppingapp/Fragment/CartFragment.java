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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.shoppingapp.Activity.MainActivity;
import com.shoppingapp.Activity.SplashActivtiy;
import com.shoppingapp.Adapter.ItemCartAdapter;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
   Button checkout ;
    RecyclerView cartView ;
    ItemCartAdapter itemCartAdapter ;
    List<ItemCart> itemCartList;
    private  final int APP_REQUEST_CODE = 100;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_cart, container, false);


        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");


                }

                @Override
                public void onError(final AccountKitError error) {
                    Toast.makeText(getActivity(), "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();



                }
            });
        } else {
            verifyMobileNumber();
        }
        checkout = view.findViewById(R.id.chechout);
        cartView = view.findViewById(R.id.cart_recycler);
        itemCartList = new ArrayList<>();
        itemCartList.add(new ItemCart("1","dress","200","1",R.drawable.dress));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        cartView.setLayoutManager(manager);
        itemCartAdapter = new ItemCartAdapter(getActivity(),itemCartList);
        cartView.setAdapter(itemCartAdapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckoutFragment fragment = new CheckoutFragment();
                FragmentsUtil.replaceFragment(getActivity(),R.id.container,fragment,true);




            }
        });


        return view;
    }
    private void verifyMobileNumber() {
        final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN);
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
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



                        }

                        @Override
                        public void onError(final AccountKitError error) {
//                            Log.e("Error", error.getUserFacingMessage());
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
