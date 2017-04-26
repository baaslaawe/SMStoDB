package com.mardyoe.smstodb.controller.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by zarulizham on 26/04/2017.
 */

public class VolleyHelper {

    private final Context mContext;
    private final RequestQueue mRequestQueue;
    //private final ImageLoader mImageLoader;
    private final String mBaseUrl;

    public VolleyHelper(Context c, String baseUrl){
        mContext = c;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mBaseUrl = baseUrl;
        //mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
    }

    private String contructUrl(String method){
        return mBaseUrl + "/" + method;
    }

//    public ImageLoader getImageLoader(){
//        return mImageLoader;
//    }

    public void get(String method, JSONObject jsonRequest,
                    Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void put(String method, JSONObject jsonRequest,
                    Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.PUT, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void post(String method, JSONObject jsonRequest,
                     Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.POST, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);

    }

    public void delete(String method, JSONObject jsonRequest,
                       Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.DELETE, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void cancelRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

}