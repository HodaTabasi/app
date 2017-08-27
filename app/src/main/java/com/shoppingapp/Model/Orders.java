package com.shoppingapp.Model;

import java.util.ArrayList;

/**
 * Created by Yasmeen on 27/08/2017.
 */

public class Orders {

    /**
     * id : 1
     * status : pending
     * user_id : 1
     * order_track : pending
     * order_date : 2017-08-24 09:30:00
     * delivered_date : 2017-08-31 05:14:14
     */

    private String id;
    private String status;
    private String user_id;
    private String order_track;
    private String order_date;
    private String delivered_date;
    private ArrayList<Order_details> order_details;

    public ArrayList<Order_details> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(ArrayList<Order_details> order_details) {
        this.order_details = order_details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_track() {
        return order_track;
    }

    public void setOrder_track(String order_track) {
        this.order_track = order_track;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDelivered_date() {
        return delivered_date;
    }

    public void setDelivered_date(String delivered_date) {
        this.delivered_date = delivered_date;
    }
}
