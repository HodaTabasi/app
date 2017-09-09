package com.shoppingapp;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

/**
 * Created by M.S.I on 9/9/2017.
 */

public class MyRequests extends Observable implements Response.Listener<JSONObject>{

    public void getItem(String url){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
