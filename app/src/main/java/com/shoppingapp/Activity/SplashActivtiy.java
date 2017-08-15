package com.shoppingapp.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.shoppingapp.R;

import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class SplashActivtiy extends AppCompatActivity implements Animation.AnimationListener{
    private static final long SPLASH_TIME_OUT = 5000;
    private static final int APP_REQUEST_CODE = 99 ;
    // Animation
        Animation anima;
        Context mContext;
private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activtiy);
        ImageView logo = (ImageView)findViewById(R.id.logo);
        anima = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        // save the context in a variable to save the current context for later use
        mContext = this;
        // set animation listener
        anima.setAnimationListener(SplashActivtiy.this);
        // start the animation
        logo.startAnimation(anima);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (AccountKit.getCurrentAccessToken() != null) {
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    Log.e("Error", "User Info Successfully");
                    Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
                    startActivity(i);

                }

                @Override
                public void onError(final AccountKitError error) {
                    Log.e("Error", error.getUserFacingMessage());
                    Toast.makeText(getApplicationContext(), error.getUserFacingMessage(), Toast.LENGTH_SHORT).show();
                }
            });

                } else {
                    phoneLogin();
                }
            }
        }, SPLASH_TIME_OUT);


//    mSplashThread =  new Thread(){
//            @Override
//            public void run(){
//                try {
//                    synchronized(this){
//                        wait(2000);
//                    }
//                }
//                catch(InterruptedException ex){
//                }
//                finish();
//
//                Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
//                startActivity(i);
//            }
//        };
//
//        mSplashThread.start();


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    public void phoneLogin() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.CODE);
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);

        startActivityForResult(intent, APP_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != APP_REQUEST_CODE) {
            return;
        }

        String toastMessage;
        AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
        if (loginResult == null || loginResult.wasCancelled()) {
            toastMessage = "Login Cancelled";
        } else if (loginResult.getError() != null) {
            toastMessage = loginResult.getError().getErrorType().getMessage();

        } else {
            final AccessToken accessToken = loginResult.getAccessToken();
            if (accessToken != null) {
                toastMessage = "Success:" + accessToken.getAccountId();
                Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
                startActivity(i);

            } else {
                toastMessage = "Unknown response type";
            }


            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }


}
