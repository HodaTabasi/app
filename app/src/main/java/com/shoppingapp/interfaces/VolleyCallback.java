package com.shoppingapp.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by M.S.I on 9/9/2017.
 */

public interface VolleyCallback {
    void onSuccessResponse(String result) throws JSONException;
}
