package com.erank.applerssfeed.models;

import java.util.List;

public class Response {
    public Feed feed;

    public static class Feed {
        public List<Data> results;
    }
}