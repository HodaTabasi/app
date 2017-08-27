package com.shoppingapp.Model;

/**
 * Created by Yasmeen on 27/08/2017.
 */

public class Category {

    /**
     * id : 1
     * name : watches
     * item_count : 1
     */

    private String id;
    private String name;
    private String item_count;

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

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }
}
