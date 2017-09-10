package com.shoppingapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.Adapter.ItemAdapterr;
import com.shoppingapp.Model.Item;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasmeen on 11/08/2017.
 */

public class AccessoriesFragment extends Fragment {
    RecyclerView AccessoriesView ;
    ItemAdapterr itemAdapter ;
    List<Item>   itemDetailsList = new ArrayList<>();;




    public AccessoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        AccessoriesView = view.findViewById(R.id.trends_recycler);

//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
//        itemDetailsList.add(new ItemDetails("1","Ray-Ban aviator sunglasses","100",R.drawable.access));
        String url = "http://127.0.0.1:8080/project/api.php?action=item&category=6";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            String id, name, price, size, favourite, detials, image , category_id, color_id;
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response!=null) {
                        JSONArray jsonArray = response.getJSONArray("item");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Item item = new Item();
                            id =obj.getString("id");
                            name = obj.getString("name");
                            price = obj.getString("price");
                            size = obj.getString("size");
                            favourite = obj.getString("favourite");
                            detials = obj.getString("detials");
                            image = obj.getString("image");
                            category_id = obj.getString("category_id");
                            color_id = obj.getString("color_id");
                            item.setId(id);
                            item.setName(name);
                            item.setPrice(price);
                            item.setSize(size);
                            item.setFavourite(favourite);
                            item.setDetials(detials);
                            item.setImage(image);
                            item.setCategory_id(category_id);
                            item.setColor_id(color_id);
                            itemDetailsList.add(item);
                            Log.e("iddddddddd",id);
                            Log.e("categoryyyyyyyyyyyyyyy",category_id);

                        }

                    }
                    }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2 );
        AccessoriesView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapterr(getActivity(),itemDetailsList);
        AccessoriesView.setAdapter(itemAdapter);
        queue.add(jsonObjectRequest);
        AccessoriesView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));

        return  view;
    }
}
