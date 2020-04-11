package com.erank.applerssfeed.utils.room.tasks;

import android.os.AsyncTask;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.utils.interfaces.GetDataListCallback;
import com.erank.applerssfeed.utils.room.DataDao;

import java.util.Date;
import java.util.List;

public class GetFilteredTask extends AsyncTask<Void,Void, List<Data>> {

    private MediaType mediaType;
    private DataDao dao;
    private String query;
    private GetDataListCallback callback;

    public GetFilteredTask(MediaType mediaType, DataDao dao, String query, GetDataListCallback callback) {

        this.mediaType = mediaType;
        this.dao = dao;
        this.query = query;
        this.callback = callback;
    }

    @Override
    protected List<Data> doInBackground(Void... voids) {
        return dao.getFiltered(mediaType,query);
    }

    @Override
    protected void onPostExecute(List<Data> list) {
        callback.onListFetched(list);
    }
}
