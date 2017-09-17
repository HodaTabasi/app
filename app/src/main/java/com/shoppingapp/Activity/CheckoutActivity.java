package com.shoppingapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payfort.start.Card;
import com.payfort.start.Start;
import com.payfort.start.Token;
import com.payfort.start.TokenCallback;
import com.payfort.start.error.CardVerificationException;
import com.payfort.start.error.StartApiException;
import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yasmeen on 21/08/2017
 */

public class CheckoutActivity extends AppCompatActivity implements TokenCallback,View.OnClickListener{

    Toolbar d_toolbar;

    Button pay;
    Start start = new Start(Constant.API_OPEN_KEY);
    EditText numberEditText, monthEditText, yearEditText, cvcEditText, ownerEditText;
    TextView total_price, errorTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);

        initComponent();

        setUpToolbar();

        initAnimation();

    }

    private void setUpToolbar() {
        setSupportActionBar(d_toolbar);
        getSupportActionBar().setTitle(R.string.chechout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initComponent() {
        d_toolbar = (Toolbar) findViewById(R.id.d_toolbar);
        pay = (Button) findViewById(R.id.pay);
        numberEditText = (EditText) findViewById(R.id.numberEditText);
        monthEditText = (EditText) findViewById(R.id.monthEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        cvcEditText = (EditText) findViewById(R.id.cvcEditText);
        ownerEditText = (EditText) findViewById(R.id.ownerEditText);
        total_price = (TextView) findViewById(R.id.total_price);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorTextView = (TextView) findViewById(R.id.errorTextView);

        total_price.setText(getIntent().getStringExtra("subtotal"));
        pay.setOnClickListener(this);
    }

    private void initAnimation(){
        Slide slide = new Slide(GravityCompat.getAbsoluteGravity(Gravity.END,getResources().getConfiguration().getLayoutDirection()));
        slide.setDuration(getResources().getInteger(R.integer.MEDIUM_DURATION));
        getWindow().setEnterTransition(slide);
    }

    public void payment() {
            try {
                Card card = unbindCard();

                errorTextView.setText(null);
                hideKeyboard();
                showProgress(true);

                int price = Integer.parseInt(total_price.getText().toString().trim());
                Log.e("ff",price+" ");
                start.createToken(this, card, this, price, "USD");
            } catch (CardVerificationException e) {
                setErrors(e.getErrorFields());
            }

    }


    private Card unbindCard() throws CardVerificationException {
            clearErrors();
            String number = unbindString(numberEditText);
            int year = unbindInteger(yearEditText);
            int month = unbindInteger(monthEditText);
            String cvc = unbindString(cvcEditText);
            String owner = unbindString(ownerEditText);
            return new Card(number, cvc, month, year, owner);

    }

    private String unbindString(EditText editText) {
        return editText.getText().toString().trim();
    }

    private int unbindInteger(EditText editText) {
        try {
            String text = unbindString(editText);
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    private void clearErrors() {
        numberEditText.setError(null);
        monthEditText.setError(null);
        yearEditText.setError(null);
        cvcEditText.setError(null);
        ownerEditText.setError(null);
    }


    private void setErrors(EnumSet<Card.Field> errors) {
        String error = getString(R.string.edit_text_invalid);

        if (errors.contains(Card.Field.NUMBER)) {
            numberEditText.setError(error);
        }
        if (errors.contains(Card.Field.EXPIRATION_YEAR)) {
            yearEditText.setError(error);
        }
        if (errors.contains(Card.Field.EXPIRATION_MONTH)) {
            monthEditText.setError(error);
        }
        if (errors.contains(Card.Field.CVC)) {
            cvcEditText.setError(error);
        }
        if (errors.contains(Card.Field.OWNER)) {
            ownerEditText.setError(error);
        }
    }


    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showProgress(boolean progressVisible) {
        pay.setEnabled(!progressVisible);
        progressBar.setVisibility(progressVisible ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onSuccess(Token token) {
        Log.d(Constant.CHECK_OUT_TAG, "Token is received: " + token);
        Toast.makeText(this, getString(R.string.congrats, token.getId()), Toast.LENGTH_LONG).show();
        showProgress(false);
    }

    @Override
    public void onError(StartApiException error) {
        Log.e(Constant.CHECK_OUT_TAG, "Error getting token", error);
        showProgress(false);

    }

    @Override
    public void onCancel() {
        Log.e(Constant.CHECK_OUT_TAG, "Getting token is canceled by user");
        showProgress(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay:
               payment();
               addNewOrder();
            break;
        }
    }

    private void addNewOrder() {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");

                    String accountKitId = account.getId();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id",accountKitId);
                    params.put("item_id","1");
                    params.put("quantity","1");
                    Calendar calendar = Calendar.getInstance();
                    params.put("order_date",calendar.getTime()+" ");


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
                    String currentDateandTime = sdf.format(new Date());
                    Date date = null;
                    try {
                        date = sdf.parse(currentDateandTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    calendar.setTime(date);
                    calendar.add(Calendar.HOUR, 24);
                    params.put("delevired_date",calendar.getTime()+" ");

                    try {
                        MyRequests.getInstance().addToDataBase(Constant.ADD_ORDER_URL, params, new VolleyCallback() {
                            @Override
                            public void onSuccessResponse(String result) throws JSONException {
                                Log.e("result_cou",result);
                                JSONObject object = new JSONObject(result);
                                JSONObject object1 = object.getJSONObject("add_order");
                                Toast.makeText(CheckoutActivity.this, object1.getString("status"), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(final AccountKitError error) {
                    Toast.makeText(CheckoutActivity.this, "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();



                }
            });
    }
}
