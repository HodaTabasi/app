package com.shoppingapp.Model;

/**
 * Created by M.S.I on 8/30/2017.
 */

public class Item {


    /**
     * id : 1
     * name : item1
     * price : 57
     * size : XL
     * favourite : 1
     * detials : made in japan
     * image : Item.jpeg
     * category_id : 1
     * color_id : 1
     */

    private String id;
    private String name;
    private String price;
    private String size;
    private String favourite;
    private String detials;
    private String image;
    private String category_id;
    private String color_id;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getDetials() {
        return detials;
    }

    public void setDetials(String detials) {
        this.detials = detials;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }
}
