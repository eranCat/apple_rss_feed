package com.erank.applerssfeed.utils;

import android.net.Uri;

public class RssBuilder {

    private static final String BASE = "rss.itunes.apple.com";

    private RssBuilder() {
    }

    public static String getUrl(Media media) {
        return new Uri.Builder()
                .scheme("https").authority(BASE)
                .appendPath("api").appendPath("v1")
                .appendPath("us")//Country
                .appendPath(media.val)
                .appendPath(media.defaultFeedType)
                .appendPath("all")
                .appendPath(ResultLimit.ALL.val)
                .appendPath(ExplicitMode.NO.val)
                .build()
                .toString() + ".json";//TODO check how to add '.something' with builder
    }
}
