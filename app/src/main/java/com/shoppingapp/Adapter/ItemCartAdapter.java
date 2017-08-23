package com.shoppingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.R;

import java.util.List;

/**
 * Created by Yasmeen on 21/08/2017.
 */
public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.RecyclerViewHolders> {

private List<ItemCart> itemCartList;
private Context context;

public ItemCartAdapter(Context context, List<ItemCart> itemDetailsList) {
        this.itemCartList = itemDetailsList;
        this.context = context;
        }

@Override
public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_to_card, null,false);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        //return new rcv(layoutView);
        return rcv;
        }
@Override
public void onBindViewHolder(RecyclerViewHolders holder, int position) {
       holder.itemName.setText(itemCartList.get(position).getName());
       holder.itemPrice.setText( itemCartList.get(position).getPrice());
    //   holder.itemColor.setBackgroundColor(itemCartList.get(position).getColor());
       holder.itemQuantity.setText(itemCartList.get(position).getQuantity());
       holder.itemImg.setImageResource(itemCartList.get(position).getImag());
        }

@Override
public int getItemCount() {
        return itemCartList.size();
        }

public class RecyclerViewHolders extends RecyclerView.ViewHolder{


    public TextView itemName ,itemPrice ,itemQuantity;
    View itemColor;
    public ImageView itemImg ;


    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.add_card_item_name);
        itemPrice = itemView.findViewById(R.id.add_cart_item_price);
        itemImg = itemView.findViewById(R.id.add_card_item_img);
        itemColor =itemView.findViewById(R.id.add_cart_item_color);
        itemQuantity =itemView.findViewById(R.id.item_count);
    }
}}


