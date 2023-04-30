package com.example.openionapp;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class OpenionApplication extends Application {
    public static final String TAG = OpenionApplication.class
            .getSimpleName();
    private RequestQueue mRequestQueue;

    private static OpenionApplication mInstance;
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public static synchronized OpenionApplication getInstance() {
        return mInstance;
    }

}
