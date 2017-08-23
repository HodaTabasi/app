package com.shoppingapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
import com.shoppingapp.R;

public class SplashActivtiy extends AppCompatActivity implements Animation.AnimationListener{
    private  final int APP_REQUEST_CODE = 100;
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
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(2000);
                    }
                }
                catch(InterruptedException ex){
                }

//
//                if (AccountKit.getCurrentAccessToken() != null) {
//                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
//                        @Override
//                        public void onSuccess(final Account account) {
//                            Log.e("Error", "User Info Successfully");
//
                            Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
                            startActivity(i);
                            finish();
//
//
//                        }
//
//                        @Override
//                        public void onError(final AccountKitError error) {
//                            Toast.makeText(mContext, "للاسف حدثت مشكلة في الخادم .. حاول مره اخري", Toast.LENGTH_SHORT).show();
//
//
//
//                        }
//                    });
//                } else {
//                    verifyMobileNumber();
//                }



 

            }
        };

        mSplashThread.start();


    }
//    private void verifyMobileNumber() {
//        final Intent intent = new Intent(this, AccountKitActivity.class);
//        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
//                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
//                LoginType.PHONE,
//                AccountKitActivity.ResponseType.TOKEN);
//        final AccountKitConfiguration configuration = configurationBuilder.build();
//        intent.putExtra(
//                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
//                configuration);
//        startActivityForResult(intent, APP_REQUEST_CODE);
//    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }





//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
//            final AccountKitLoginResult loginResult = AccountKit.loginResultWithIntent(data);
//            if (loginResult == null || loginResult.wasCancelled()) {
//                Toast.makeText(getApplicationContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
//            } else if (loginResult.getError() != null) {
//                Toast.makeText(getApplicationContext(), loginResult.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
//            } else {
//                final AccessToken accessToken = loginResult.getAccessToken();
//
//                if (accessToken != null) {
//                    Log.e("Error", "Success:" + accessToken.getAccountId());
//                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
//                        @Override
//                        public void onSuccess(final Account account) {
//                            Log.e("Error", "User Info Successfully");
//                            Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
//                            startActivity(i);
//                            finish();
//
//
//                        }
//
//                        @Override
//                        public void onError(final AccountKitError error) {
////                            Log.e("Error", error.getUserFacingMessage());
//                            Toast.makeText(getApplicationContext(), error.getUserFacingMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    Toast.makeText(getApplicationContext(), "Unknown response type", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }


}
