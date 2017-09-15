package com.shoppingapp.Fragment;

import android.app.ActivityOptions;
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
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.Adapter.ItemAdapter;
import com.shoppingapp.Adapter.ItemDetailAdapter;
import com.shoppingapp.Model.Item;
import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.MyRecyclerItemClickListener;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by DevAmar on 9/9/17
 */

public class ShowItemFragment extends Fragment implements Observer{
//    MyRecyclerItemClickListener.OnItemClickListener,

    private View mView;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ItemAdapter mItemAdapter ;
    private List<Item> mItemDetailsList = new ArrayList<>();
    private String url;
    private int type;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recycler,container ,false);

        initView();

        setUpRecyclerView();
        MyRequests.getInstance().addObserver(this);

        if(type == 0) {
            MyRequests.getInstance().getItem(url);
        }else {
            MyRequests.getInstance().getFav(url,type);
        }

        return mView;
    }



    private void initView() {
        mRecyclerView = mView.findViewById(R.id.rv);
        url = getArguments().getString("url");
        type = getArguments().getInt("type");
        mProgressBar = mView.findViewById(R.id.loader);
    }

    private void setUpRecyclerView() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2 );
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mItemAdapter = new ItemAdapter(getContext(),mItemDetailsList);
        mRecyclerView.setAdapter(mItemAdapter);
//        mRecyclerView.addOnItemTouchListener(new MyRecyclerItemClickListener(getContext(), this));
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        Intent intent = new Intent(getActivity(),ItemDetailsActivity.class);
//        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity());
//        startActivity(intent , activityOptions.toBundle());
//    }

    @Override
    public void update(Observable observable, Object o) {
        JSONObject jsonObject = (JSONObject) o;
        Log.e("Observer", jsonObject.toString());
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            mItemDetailsList.clear();
            mProgressBar.setVisibility(View.INVISIBLE);
                JSONArray s = jsonObject.getJSONArray("'item'");
                Log.e("fff", s + " ");
                for (int i = 0; i < s.length(); i++) {
                    JSONObject object = s.getJSONObject(i);
                    Item item = gson.fromJson(object.toString(), Item.class);
                    mItemDetailsList.add(item);
                    mItemAdapter.notifyDataSetChanged();
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
