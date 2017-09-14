package com.shoppingapp;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

/**
 * Created by M.S.I on 9/9/2017
 */

public class MyRequests extends Observable implements Response.Listener<JSONObject>{

    private static MyRequests myRequests;
    private MyRequests(){}

    public static MyRequests getInstance(){
        if(myRequests == null){
            myRequests = new MyRequests();
        }
        return myRequests;

    }
    public void getItem(String url){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void addFavorite(String url,  Map data){

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Observer2", "ffff"+response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",error.getMessage());
                }
            });
            UIApplication.getInstance().addRequestQueue(jsonObjectRequest);

    }



    @Override
    public void onResponse(JSONObject response) {
        setChanged();
        notifyObservers(response);
    }
}
