package com.shoppingapp;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by M.S.I on 9/9/2017.
 */

class MyLruCache implements ImageLoader.ImageCache {
    private final LruCache<String, Bitmap>
            cache = new LruCache<String, Bitmap>(20);


    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
