package com.erank.applerssfeed.utils;

import com.erank.applerssfeed.models.Data;

import java.util.List;

public interface DataFetchedCallback {
    void onDataFetched(List<Data> list, Media type);

    void onErrorFetching(Exception e);
}
