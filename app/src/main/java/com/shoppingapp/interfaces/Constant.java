package com.shoppingapp.interfaces;

import com.shoppingapp.Activity.CheckoutActivity;

/**
 * Created by DevAmar on 9/9/17
 */

public interface Constant {
    String API_OPEN_KEY = "test_open_k_f7b67d62f01ec766683e";
    String CHECK_OUT_TAG = CheckoutActivity.class.getSimpleName();
    int APP_REQUEST_CODE = 100;
    String url = "http://evlope.com/rawnaq/api.php";
    String IMG_PATH = "http://evlope.com/rawnaq/img/";
    String ADD_FAVORITE_URL = "http://evlope.com/rawnaq/add_to_fav.php";
    String ADD_TO_CART_URL = "http://evlope.com/rawnaq/add_cart.php";
    String ADD_USER_URL = "http://evlope.com/rawnaq/add_user.php";    //POST : mobile , acess_token
    String ADD_ORDER_URL = "http://evlope.com/rawnaq/add_order.php";
    /*Action : post
     Parameter :
     user_id, item_id, quantity,
     order_date : yyyy-mm-dd
     delevired_date : yyyy-mm-dd*/
    String GET_FAVORITE_URL = "http://evlope.com/rawnaq/get_fav.php";
    String GET_USER_INFO_URL = "http://evlope.com/rawnaq/user.php";   //POST : user_id
    String GET_CART_URL = "http://evlope.com/rawnaq/get_carts.php";   //POST : uesr_id
    String GET_ORDER_URL = "http://evlope.com/rawnaq/get_orders.php"; //POST : uesr_id
    String UPDATE_QUANTITY_URL = "http://evlope.com/rawnaq/edit_quantity.php";  // POST : cart_id , quantity
    String UPDATE_PROFILE_URL = "http://evlope.com/rawnaq/edit_user.php"; //post :user_id,name,email,address
    String DELETE_CART_URL = "http://evlope.com/rawnaq/delete_cart.php";  // POST : cart_id
    int FRAGMENT_ONE = 0;
    int FRAGMENT_TWO = 1;
    int FRAGMENT_THREE = 2;
    int FRAGMENT_FOUR = 3;
    int FRAGMENT_FIVE = 4;
    int FRAGMENT_SIX = 5;
}
