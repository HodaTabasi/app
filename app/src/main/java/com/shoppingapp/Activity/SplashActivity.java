package com.shoppingapp.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoppingapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activtiy);

        Thread mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                goToMainActivity();
            }
        };

        mSplashThread.start();


    }

    private void goToMainActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this);
                startActivity(i, activityOptions.toBundle());
                finish();
            }
        });

    }


}
