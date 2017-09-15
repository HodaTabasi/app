package com.shoppingapp;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.accountkit.AccountKit;
import com.shoppingapp.interfaces.VolleyCallback;
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
    public void getFav(String url, final int type){

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    setChanged();
                    notifyObservers(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", AccountKit.getCurrentAccessToken().getAccountId());
                params.put("cat_id", String.valueOf(type));
                return params;
            }
        };
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void addToDataBase(String url, final Map data,final VolleyCallback callback) throws JSONException {

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            callback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                return data;
            }
        };
        UIApplication.getInstance().addRequestQueue(strRequest);
    }

    public void getUserInfo(String url, final Map data, final VolleyCallback volleyCallback){
        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        System.out.println("response is"+response);
                        try {
                            volleyCallback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                return data;
            }
        };
        UIApplication.getInstance().addRequestQueue(strRequest);

    }

    public void addOrder(String url, final Map data) throws JSONException {

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject object1  = object.getJSONObject("add_order");
                            Toast.makeText(getApplicationContext(), "Order Status : "+ object1.getString("status") , Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                return data;
            }
        };
        UIApplication.getInstance().addRequestQueue(strRequest);


    }

    @Override
    public void onResponse(JSONObject response) {
        setChanged();
        notifyObservers(response);
    }
}
