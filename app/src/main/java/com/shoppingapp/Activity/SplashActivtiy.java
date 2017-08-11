package com.shoppingapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.shoppingapp.R;

public class SplashActivtiy extends AppCompatActivity implements Animation.AnimationListener{
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
                finish();

                Intent i = new Intent(SplashActivtiy.this,MainActivity.class);
                startActivity(i);
            }
        };

        mSplashThread.start();


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

}
