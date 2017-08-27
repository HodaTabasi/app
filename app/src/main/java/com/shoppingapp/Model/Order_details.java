package com.shoppingapp.Model;

/**
 * Created by Yasmeen on 27/08/2017.
 */
public class Order_details {

    /**
     * item_id : 1
     * quantity : 2
     * price : 57
     */

    private String item_id;
    private String quantity;
    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
