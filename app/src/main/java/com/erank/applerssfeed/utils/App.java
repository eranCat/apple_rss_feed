package com.erank.applerssfeed.utils;

import android.app.Application;

import com.erank.applerssfeed.utils.room.DataSource;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataSource.init(this);
    }
}
