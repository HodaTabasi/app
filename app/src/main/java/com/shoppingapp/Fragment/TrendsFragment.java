package com.shoppingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.Adapter.ItemAdapterr;
import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.Item;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TrendsFragment extends Fragment implements Observer {
    RecyclerView trendsView;
    ItemAdapterr itemAdapter;
    List<Item> itemDetailsList;


    public TrendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MyRequests requests = new MyRequests();
        requests.addObserver(this);
        String url = "http://evlope.com/rawnaq/api.php?action=item&category=1";
        requests.getItem(url);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        trendsView = view.findViewById(R.id.trends_recycler);
        itemDetailsList = new ArrayList<>();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        trendsView.setLayoutManager(linearLayoutManager);
        Log.e("itemDetailsListhere",itemDetailsList.size()+"");
        itemAdapter = new ItemAdapterr(getActivity(), itemDetailsList);
        trendsView.setAdapter(itemAdapter);
        trendsView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                startActivity(intent);
            }
        }));

        return view;
    }



    @Override
    public void update(Observable observable, Object o) {
        JSONObject jsonObject = (JSONObject) o;
        Log.e("Observer", jsonObject.toString());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JSONArray s = null;
        try {
            s = jsonObject.getJSONArray("'item'");
            Log.e("fff",s+" ");
            for (int i = 0 ; i<=s.length() ;i++ ){
                JSONObject object = s.getJSONObject(i);
                Item item = gson.fromJson(object.toString(),Item.class);
                itemDetailsList.add(item);
                Log.e("id",itemDetailsList.get(i).getId()+" : "+itemDetailsList.size());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
