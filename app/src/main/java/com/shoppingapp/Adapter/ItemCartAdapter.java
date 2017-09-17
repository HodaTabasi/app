package com.shoppingapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.shoppingapp.Model.ItemCart;
import com.shoppingapp.MyRequests;
import com.shoppingapp.R;
import com.shoppingapp.interfaces.Constant;
import com.shoppingapp.interfaces.MyInterFace;
import com.shoppingapp.interfaces.VolleyCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yasmeen on 21/08/2017
 */
public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.RecyclerViewHolders> {

    private List<ItemCart> itemCartList;

    int count = 0;
    Context context;
    MyInterFace myInterFace;
    int subtotal = 0;

    public ItemCartAdapter( Context context, List<ItemCart> itemDetailsList,MyInterFace myInterFace) {
        this.context = context;
        this.itemCartList = itemDetailsList;
        this.myInterFace = myInterFace;

    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_to_card, null, false);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        if(count == 0)
          calculatePrice();

        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        holder.itemName.setText(itemCartList.get(position).getName());
       int qprice= parse(itemCartList.get(position).getPrice())*parse(itemCartList.get(position).getQuantity());
        holder.itemPrice.setText(itemCartList.get(position).getPrice());
        holder.itemQuantity.setText(itemCartList.get(position).getQuantity());
        Picasso.with(context).load(Constant.IMG_PATH + itemCartList.get(position).getImage()).into(holder.itemImg);
//        subtotal+=qprice;

        holder.count_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> params = new HashMap<String, String>();
                params.put("cart_id",itemCartList.get(position).getCart_id());
                params.put("quantity",(parse(itemCartList.get(position).getQuantity())+1)+"");

                editQuantity(params,itemCartList.get(position),true);
                holder.itemQuantity.setText(itemCartList.get(position).getQuantity());
            }
        });

        holder.count_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> params = new HashMap<String, String>();
                params.put("cart_id",itemCartList.get(position).getCart_id());
                params.put("quantity",(parse(itemCartList.get(position).getQuantity())-1)+"");

                editQuantity(params,itemCartList.get(position),false);
                holder.itemQuantity.setText(itemCartList.get(position).getQuantity());

            }
        });

        holder.delete_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,String> params = new HashMap<String, String>();
                params.put("cart_id",itemCartList.get(position).getCart_id());
                Log.e("cart_id",itemCartList.get(position).getId());

                deleteCart(params,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemCartList.size();
    }

     class RecyclerViewHolders extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQuantity;
        View itemColor;
        ImageView itemImg , count_up, count_down;
         ImageButton delete_cart;
        RecyclerViewHolders(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.add_card_item_name);
            itemPrice = itemView.findViewById(R.id.add_cart_item_price);
            itemImg = itemView.findViewById(R.id.add_card_item_img);
            itemColor = itemView.findViewById(R.id.add_cart_item_color);
            itemQuantity = itemView.findViewById(R.id.item_count);
            count_up = itemView.findViewById(R.id.count_up);
            count_down = itemView.findViewById(R.id.count_down);
            delete_cart = itemView.findViewById(R.id.delete_cart);
        }
    }


    private void deleteCart(final Map<String, String> params, final int i) {
        Log.e("cart_id",params.get("cart_id"));
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                Log.e("Error", "User Info Successfully");

                MyRequests.getInstance().getData(Constant.DELETE_CART_URL, params, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) throws JSONException {
                        Log.e("result_eqi",result);
                        JSONObject object =new JSONObject(result);
                        JSONObject object1 = object.getJSONObject("delete_cart");
                        String s = object1.getString("status");
                        if(s.equals("success")){
                            itemCartList.remove(i);
                            notifyDataSetChanged();
                        }

                    }
                });

            }

            @Override
            public void onError(final AccountKitError error) {
            }
        });
    }


    private void editQuantity(final Map<String,String> params, final ItemCart item, final Boolean aBoolean){

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                Log.e("Error", "User Info Successfully");

                MyRequests.getInstance().getData(Constant.UPDATE_QUANTITY_URL, params, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) throws JSONException {
                        Log.e("result_eqi",result);
                        JSONObject object =new JSONObject(result);
                        JSONObject object1 = object.getJSONObject("update_cart");
                        String s = object1.getString("status");
                        String s1 = object1.getString("quantity");
                        item.setQuantity(s1);
                        if(aBoolean) {
                            subtotal += parse(item.getPrice());
                        }else {
                            subtotal -= parse(item.getPrice());
                        }

                        myInterFace.onItemSelected(subtotal+"");
                        notifyDataSetChanged();

                    }
                });

            }

            @Override
            public void onError(final AccountKitError error) {
            }
        });

    }

    private int parse(String s){
       return Integer.parseInt(s);
    }

    private void calculatePrice() {
        for(ItemCart cart :itemCartList){
            subtotal+=(parse(cart.getPrice())*parse(cart.getQuantity()));
            Log.e("dffb",cart.getPrice());
        }
         count++;
         myInterFace.onItemSelected(subtotal+"");
    }

}


