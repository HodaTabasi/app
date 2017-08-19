package com.shoppingapp.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoppingapp.FragmentsUtil;
import com.shoppingapp.Model.Order;
import com.shoppingapp.R;

import java.util.ArrayList;

/**
 Created by DevAmar on 8/14/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    private ArrayList<Order> orders;
    private LayoutInflater layoutInflater;
    private Context context;

    public OrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recycler_active_content , parent, false);
        return new OrderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        Order order = orders.get(position);
        FragmentsUtil.setSpannableString(context.getString(R.string.order_no) +": "+order.getOrderNumber() , R.color.gray_blue,R.color.black_light
                ,holder.orderNumber);
        FragmentsUtil.setSpannableString(context.getString(R.string.track) +": "+order.getTrack() , R.color.gray_blue,R.color.black_light
                ,holder.track);
        FragmentsUtil.setSpannableString(context.getString(R.string.estimated_delivered_date) +": "+order.getDeliveredDate() , R.color.gray_blue,R.color.black_light
                ,holder.deliveredDate);
        holder.status.setText(order.getStatus());
        holder.date.setText(order.getOrderDate());
        setStatusColor(order.getStatus() , holder.status);
        setVisibility(order.getStatus() , holder);
    }

    private void setStatusColor(String statusVal, TextView status){
        switch (statusVal){
            case "Order delivered":
                status.setTextColor(ContextCompat.getColor(context ,R.color.blue));
            break;
            case "Order on delivery":
                status.setTextColor(ContextCompat.getColor(context ,R.color.green));
            break;
            case "Processed":
                status.setTextColor(ContextCompat.getColor(context ,R.color.black));
            break;
        }
    }

    private void setVisibility(String statusVal , OrderHolder orderHolder){
        switch (statusVal){
            case "Order delivered":
                orderHolder.deliveredDate.setVisibility(View.INVISIBLE);
                break;
            case "Processed":
                orderHolder.track.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {
        TextView orderNumber;
        TextView track;
        TextView status;
        TextView date;
        TextView deliveredDate;
         OrderHolder(View itemView) {
            super(itemView);
             orderNumber = itemView.findViewById(R.id.order_num);
             track =  itemView.findViewById(R.id.track);
             status = itemView.findViewById(R.id.status);
             date =  itemView.findViewById(R.id.order_date);
             deliveredDate = itemView.findViewById(R.id.delivered_date);

        }
    }
}
