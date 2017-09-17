package com.shoppingapp.Model;

/**
 * Created by DevAmar on 8/14/17.
 */

public class Order {

    private String id;
    private String status;
    private String user_id;
    private String order_date;
    private String delivered_date;
    private String item_id;
    private String quantity;

    public Order(String id, String status, String user_id, String order_date, String delivered_date) {
        this.id = id;
        this.status = status;
        this.user_id = user_id;
        this.order_date = order_date;
        this.delivered_date = delivered_date;
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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
