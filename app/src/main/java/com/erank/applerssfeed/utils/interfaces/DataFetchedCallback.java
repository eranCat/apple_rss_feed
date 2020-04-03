package com.erank.applerssfeed.utils.interfaces;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;

import java.util.List;

public interface DataFetchedCallback {
    void onDataFetched(List<Data> list, MediaType type);

    void onErrorFetching(Exception e);
}
