package com.shoppingapp.Model;

/**
 * Created by Yasmeen on 27/08/2017.
 */

public class User {

    /**
     * id : 1
     * name : Ohood
     * email : engohood.1986@gmail.com
     * address : Palestine - Gaza strip
     * mobile : 599052362
     * photo : x.jpeg
     * access_token : 123456789
     */

    private String id;
    private String name;
    private String email;
    private String address;
    private String mobile;
    private String photo;
    private String access_token;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
