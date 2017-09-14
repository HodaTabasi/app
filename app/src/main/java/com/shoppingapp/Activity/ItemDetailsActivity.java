package com.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.shoppingapp.Model.Item;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.VolleyCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Yasmeen on 11/08/2017
 */

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    Button addCardBtn;
    Toolbar d_toolbar;
    ImageView add_whishlist_btn, item_image;
    TextView item_name_m, item_size, item_prices;
    Bundle data;
    Item item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        initComponent();

        setUpToolbar();

        componentContent();

        initAnimation();

        MyRequests.getInstance().addObserver(this);


    }


    private void initComponent() {
        data = getIntent().getExtras();
        item = (Item) data.getParcelable("Item");
        d_toolbar = (Toolbar) findViewById(R.id.d_toolbar);
        item_image = (ImageView) findViewById(R.id.item_image);
        item_name_m = (TextView) findViewById(R.id.item_name_m);
        item_size = (TextView) findViewById(R.id.item_size);
        item_prices = (TextView) findViewById(R.id.item_prices);
        addCardBtn = (Button) findViewById(R.id.add_cart_btn);
        addCardBtn.setOnClickListener(this);
        add_whishlist_btn = (ImageView) findViewById(R.id.add_whishlist_btn);
        add_whishlist_btn.setOnClickListener(this);
    }

    private void setUpToolbar() {
        setSupportActionBar(d_toolbar);
        getSupportActionBar().setTitle(R.string.item_detalis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void componentContent() {
        Picasso.with(this).load(Constant.IMG_PATH + item.getImage()).into(item_image);
        item_name_m.setText(item.getName());
        item_size.setText(item.getSize());
        item_prices.setText(item.getPrice());
    }

    private void initAnimation() {
        Slide slide = new Slide(GravityCompat.getAbsoluteGravity(Gravity.END, getResources().getConfiguration().getLayoutDirection()));
        slide.setDuration(getResources().getInteger(R.integer.MEDIUM_DURATION));
        getWindow().setEnterTransition(slide);
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
        if (id == android.R.id.home) {

        }
        return super.onOptionsItemSelected(item);
    }

    private void addCart() {
        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");
//                    Toast.makeText(ItemDetailsActivity.this, "Has been added to Cart", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(final AccountKitError error) {
                    Toast.makeText(ItemDetailsActivity.this, "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            verifyMobileNumber();
        }
    }

    private void addToFavorite() {
        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("item_id", item.getId());
                    params.put("user_id", "1");
                    params.put("cat_id", item.getCategory_id());

                    try {
                        MyRequests.getInstance().addFavorite(Constant.ADD_FAVORITE_URL, params, new VolleyCallback() {
                            @Override
                            public void onSuccessResponse(String result) throws JSONException {
                                JSONObject object = new JSONObject(result);
                                JSONObject object1 = object.getJSONObject("favorite");
                                Toast.makeText(getApplicationContext(), "Order Status Is : Add To Favorite " + object1.getString("status"), Toast.LENGTH_SHORT).show();
//                                if(object1.getString("status").equals("success")){
//                                    Toast.makeText(getApplicationContext(), "Order Status Is : Add To Favorite " + object1.getString("status"), Toast.LENGTH_SHORT).show();
//                                    add_whishlist_btn.setImageResource(R.drawable.ic_like);
//                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String accountKitId = account.getId();
                    Log.e("data", accountKitId + " " + item.getId() + " " + item.getCategory_id());

                }

                @Override
                public void onError(final AccountKitError error) {
                    Toast.makeText(ItemDetailsActivity.this, "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            verifyMobileNumber();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_cart_btn:
                addCart();
                break;
            case R.id.add_whishlist_btn:
                addToFavorite();
                break;
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        JSONObject jsonObject = (JSONObject) o;
        Log.e("Observer1", "ffff" + jsonObject.toString());
    }
}





