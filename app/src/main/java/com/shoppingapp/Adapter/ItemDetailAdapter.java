package com.shoppingapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingapp.Model.ItemDetails;
import com.shoppingapp.R;

import java.util.List;

/**
 * Created by Yasmeen on 05/08/2017
 */

public class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.RecyclerViewHolders> {

    private List<ItemDetails> itemDetailsList;

    public ItemDetailAdapter(List<ItemDetails> itemDetailsList) {
        this.itemDetailsList = itemDetailsList;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        //return new rcv(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.itemName.setText(itemDetailsList.get(position).getName());
        holder.itemPrice.setText(itemDetailsList.get(position).getPrice());
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
