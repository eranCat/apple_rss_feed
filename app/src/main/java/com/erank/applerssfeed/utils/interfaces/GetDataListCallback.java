package com.erank.applerssfeed.utils.interfaces;

import com.erank.applerssfeed.models.Data;

import java.util.List;

public interface GetDataListCallback {
    void onListFetched(List<Data> list);
}
