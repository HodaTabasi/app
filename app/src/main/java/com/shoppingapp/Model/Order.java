package com.shoppingapp.Model;

/**
 * Created by DevAmar on 8/14/17.
 */

public class Order {

    private int orderNumber;
    private String track;
    private String orderDate;
    private String Status;
    private String deliveredDate;

    public Order(int orderNumber, String track, String orderDate, String status, String deliveredDate) {
        this.orderNumber = orderNumber;
        this.track = track;
        this.orderDate = orderDate;
        Status = status;
        this.deliveredDate = deliveredDate;
    }

    public Order(){}

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}
