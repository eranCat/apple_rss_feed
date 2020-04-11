package com.erank.applerssfeed.utils.interfaces;

import com.erank.applerssfeed.models.Data;

import java.util.List;

public interface DataFetchedCallback {
    void onDataFetched(List<Data> list);

    void onErrorFetching(Exception e);
}
