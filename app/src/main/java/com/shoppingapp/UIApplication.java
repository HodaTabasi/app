package com.shoppingapp;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by M.S.I on 9/9/2017.
 */

public class UIApplication extends Application {

    private static UIApplication anInstance = null;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        anInstance = this;
    }

    public synchronized static UIApplication getInstance(){
        return anInstance;
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }

    public void addRequestQueue(Request request){
        getRequestQueue().add(request);
    }

    public void addRequestQueue(Request request,String tag){
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelRequest(String tag){
        getRequestQueue().cancelAll(tag);
    }



}

