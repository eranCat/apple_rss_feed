package com.erank.applerssfeed.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.models.Response;
import com.erank.applerssfeed.utils.interfaces.DataFetchedCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ResultsFetcher {
    private ResultsFetcher() {
    }

    public static void getData(Context context, MediaType mediaType,
                               DataFetchedCallback callback) {

        StringRequest request = getStringRequest(mediaType, callback);

        Volley.newRequestQueue(context).add(request);
    }

    private static StringRequest getStringRequest(MediaType mediaType, DataFetchedCallback callback) {
        String url = RssBuilder.getUrl(mediaType);
        return new StringRequest(Request.Method.GET, url, json -> {

            Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();

            Response responseObj = gson.fromJson(json, Response.class);

            List<Data> results = responseObj.feed.results;

            results.forEach(data -> data.setType(mediaType));

            callback.onDataFetched(results, mediaType);

        }, callback::onErrorFetching);
    }
}

