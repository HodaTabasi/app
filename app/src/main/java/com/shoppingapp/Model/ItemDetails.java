package com.shoppingapp.Model;

/**
 * Created by Yasmeen on 05/08/2017.
 */

public class ItemDetails {
    private String id ;
    private  String name ;
    private String price ;
    private  String img ;


    public ItemDetails(String id, String name, String price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
