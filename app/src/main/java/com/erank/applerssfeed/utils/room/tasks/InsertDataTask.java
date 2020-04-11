package com.erank.applerssfeed.utils.room.tasks;

import android.os.AsyncTask;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.utils.interfaces.DataFetchedCallback;
import com.erank.applerssfeed.utils.room.DataDao;

import java.util.List;

public class InsertDataTask extends AsyncTask<Void, Void, Void> {
    private final List<Data> list;
    private final DataFetchedCallback callback;
    private DataDao dataDao;

    public InsertDataTask(List<Data> list, DataDao dataDao, DataFetchedCallback callback) {
        this.list = list;
        this.dataDao = dataDao;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dataDao.insertDataList(list);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.onDataFetched(list);
    }
}
