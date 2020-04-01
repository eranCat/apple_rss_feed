package com.erank.applerssfeed.utils;

public enum Media {
    MUSIC("itunes-music", "hot-tracks"),
    APPS("ios-apps", "top-grossing"),
    SHOWS("tv-shows", "top-tv-seasons"),
    MOVIES("movies", "top-movies"),
    PODCASTS("podcasts", "top-podcasts");

    public final String defaultFeedType;
    public final String val;

    Media(String val, String defaultFeedType) {
        this.val = val;
        this.defaultFeedType = defaultFeedType;
    }
}
