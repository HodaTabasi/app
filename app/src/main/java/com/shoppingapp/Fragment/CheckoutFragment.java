package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.payfort.start.Card;
import com.payfort.start.Start;
import com.payfort.start.Token;
import com.payfort.start.TokenCallback;
import com.payfort.start.error.CardVerificationException;
import com.payfort.start.error.StartApiException;
import com.shoppingapp.Dialog.DeliveryDialog;
import com.shoppingapp.R;

import java.util.EnumSet;

/**
 * Created by Yasmeen on 21/08/2017.
 */

public class CheckoutFragment extends Fragment implements TokenCallback {
    private static final String API_OPEN_KEY = "test_open_k_f7b67d62f01ec766683e";
    private static final String TAG = CheckoutFragment.class.getSimpleName();

    Button pay ;
    Start start = new Start(API_OPEN_KEY);
    EditText numberEditText, monthEditText, yearEditText, cvcEditText, ownerEditText;
    TextView total_price;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        pay = view.findViewById(R.id.pay);
        numberEditText = view.findViewById(R.id.numberEditText);
        monthEditText = view.findViewById(R.id.monthEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        cvcEditText = view.findViewById(R.id.cvcEditText);
        ownerEditText = view.findViewById(R.id.ownerEditText);
        total_price = view.findViewById(R.id.total_price);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeliveryDialog deliveryDialog = new DeliveryDialog(getContext());
//                deliveryDialog.show();
                payment(view);
            }
        });



        return view;
    }

    public void payment(View view) {
        try {
            Card card = unbindCard();
           int totalPrice= Integer.parseInt(total_price.getText().toString().trim());

            start.createToken(getActivity(), card, this, totalPrice, "USD");
        } catch (CardVerificationException e) {
            setErrors(e.getErrorFields());
        }
    }


    private Card unbindCard() throws CardVerificationException {
        clearErrors();
        String number = numberEditText.getText().toString().trim();
        int year = Integer.parseInt(yearEditText.getText().toString().trim());
        int month = Integer.parseInt(monthEditText.getText().toString().trim());
        String cvc = cvcEditText.getText().toString().trim();
        String owner = ownerEditText.getText().toString().trim();
        return new Card(number, cvc, month, year, owner);
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


    @Override
    public void onSuccess(Token token) {
        Log.d(TAG, "Token is received: " + token);
        Toast.makeText(getActivity(), getString(R.string.congrats, token.getId()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(StartApiException error) {
        Log.e(TAG, "Error getting token", error);

    }

    @Override
    public void onCancel() {
        Log.e(TAG, "Getting token is canceled by user");
    }
}
