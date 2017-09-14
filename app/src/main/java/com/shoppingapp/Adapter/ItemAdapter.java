package com.shoppingapp.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingapp.Activity.ItemDetailsActivity;
import com.shoppingapp.Model.Item;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yasmeen on 05/08/2017
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RecyclerViewHolders> {

    private List<Item> itemDetailsList;
    private Context context;

    public ItemAdapter(Context context, List<Item> itemDetailsList) {
        this.itemDetailsList = itemDetailsList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null, false);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        //return new rcv(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        holder.itemName.setText(itemDetailsList.get(position).getName());
        holder.itemPrice.setText(itemDetailsList.get(position).getPrice());
        System.out.println("image is "+Constant.IMG_PATH +""+ itemDetailsList.get(position).getImage());
        Picasso.with(context).load(Constant.IMG_PATH + itemDetailsList.get(position).getImage()).into(holder.itemImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ItemDetailsActivity.class);
                intent.putExtra("Item",itemDetailsList.get(position));
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
                context.startActivity(intent , activityOptions.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemDetailsList.size();
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        ImageView itemImg;

        RecyclerViewHolders(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImg = itemView.findViewById(R.id.item_img);
        }
    }
}
