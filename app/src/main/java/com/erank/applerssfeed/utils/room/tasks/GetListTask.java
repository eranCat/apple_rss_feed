package com.erank.applerssfeed.utils.room.tasks;

import android.os.AsyncTask;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.models.SortType;
import com.erank.applerssfeed.utils.interfaces.GetDataListCallback;
import com.erank.applerssfeed.utils.room.DataDao;

import java.util.ArrayList;
import java.util.List;

public class GetListTask extends AsyncTask<Void, Void, List<Data>> {
    private MediaType mediaType;
    private SortType sortType;
    private GetDataListCallback callback;
    private DataDao dataDao;

    public GetListTask(MediaType mediaType, SortType sortType,
                       DataDao dataDao, GetDataListCallback callback) {
        this.mediaType = mediaType;
        this.sortType = sortType;
        this.dataDao = dataDao;
        this.callback = callback;
    }

    @Override
    protected List<Data> doInBackground(Void... voids) {

        switch (sortType) {
            case NAME:
                return dataDao.getAllOrderedByName(mediaType);
            case DATE:
                return dataDao.getAllOrderedByDate(mediaType);
            case GENRE:
                return dataDao.getAllOrderedByGenre(mediaType);
            case NONE:
                return dataDao.getAll(mediaType);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Data> list) {
        callback.onListFetched(list);
    }
}
