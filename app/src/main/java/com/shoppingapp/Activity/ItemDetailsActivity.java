package com.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class ItemDetailsActivity extends AppCompatActivity {
Button addCardBtn;
    private  final int APP_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getSupportActionBar().setTitle(R.string.item_detalis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        addCardBtn = (Button) findViewById(R.id.add_cart_btn);
        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                CartFragment fragment = new CartFragment();
//                FragmentsUtil.replaceFragment(getActivity(),R.id.container,fragment,true);


                if (AccountKit.getCurrentAccessToken() != null) {
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            Log.e("Error", "User Info Successfully");

                            Toast.makeText(view.getContext(), "Has been added to Cart", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onError(final AccountKitError error) {
                            Toast.makeText(view.getContext(), "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();



                        }
                    });
                } else {
                    verifyMobileNumber();
                }



            }});
    }


    private void verifyMobileNumber() {
        final Intent intent = new Intent(ItemDetailsActivity.this, AccountKitActivity.class);
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
                        Toast.makeText(ItemDetailsActivity.this, "Has been added to Cart", Toast.LENGTH_SHORT).show();


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){

        }
        return super.onOptionsItemSelected(item);
    }
}





