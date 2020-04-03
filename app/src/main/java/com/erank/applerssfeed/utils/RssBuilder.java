package com.erank.applerssfeed.utils;

import android.net.Uri;

import com.erank.applerssfeed.models.ExplicitMode;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.models.ResultLimit;

public class RssBuilder {

    private static final String BASE = "rss.itunes.apple.com";

    private RssBuilder() {
    }

    public static String getUrl(MediaType mediaType) {
        return new Uri.Builder()
                .scheme("https").authority(BASE)
                .appendPath("api").appendPath("v1")
                .appendPath("us")//Country
                .appendPath(mediaType.val)
                .appendPath(mediaType.defaultFeedType)
                .appendPath("all")
                .appendPath(ResultLimit.ALL.val)
                .appendPath(ExplicitMode.NO.val)
                .build()
                .toString() + ".json";//TODO check how to add '.something' with builder
    }
}
