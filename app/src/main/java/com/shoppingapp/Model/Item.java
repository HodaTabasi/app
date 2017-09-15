package com.shoppingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by M.S.I on 8/30/2017
 */

public class Item implements Parcelable {


    /**
     * id : 1
     * name : item1
     * price : 57
     * size : XL
     * favourite : 1
     * details : made in japan
     * image : Item.jpeg
     * category_id : 1
     * color_id : 1
     */

    private String id;
    private String name;
    private String price;
    private String size;
    private String favourite;
    private String details;
    private String image;
    private String category_id;
    private String color_id;



    protected Item(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        size = in.readString();
        favourite = in.readString();
        details = in.readString();
        image = in.readString();
        category_id = in.readString();
        color_id = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(size);
        parcel.writeString(favourite);
        parcel.writeString(details);
        parcel.writeString(image);
        parcel.writeString(category_id);
        parcel.writeString(color_id);
    }
}
