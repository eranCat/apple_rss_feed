package com.erank.applerssfeed.utils.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.models.SortType;
import com.erank.applerssfeed.utils.ResultsFetcher;
import com.erank.applerssfeed.utils.interfaces.DataFetchedCallback;
import com.erank.applerssfeed.utils.interfaces.GetDataCallback;
import com.erank.applerssfeed.utils.interfaces.GetDataListCallback;
import com.erank.applerssfeed.utils.room.tasks.GetFilteredTask;
import com.erank.applerssfeed.utils.room.tasks.GetListTask;
import com.erank.applerssfeed.utils.room.tasks.InsertDataTask;

import java.util.List;

public class DataSource {

    private static final DataSource INSTANCE = new DataSource();
    private static final String DB_NAME = "db_apple_data";

    private DataDao dataDao;

    private DataSource() {
    }

    public static void getData(Context context, MediaType type,
                               DataFetchedCallback callback) {
        ResultsFetcher.getData(context, type, new DataFetchedCallback() {
            @Override
            public void onDataFetched(List<Data> list) {
                insertTask(list, callback);
            }

            @Override
            public void onErrorFetching(Exception e) {
                callback.onErrorFetching(e);
            }
        });
    }

    private static void insertTask(List<Data> list, DataFetchedCallback callback) {
        new InsertDataTask(list, getDao(), callback).execute();
    }

    private static DataDao getDao() {
        return INSTANCE.dataDao;
    }

    public static void getData(String id, GetDataCallback callback) {
        new AsyncTask<Void, Void, Data>() {
            @Override
            protected Data doInBackground(Void... voids) {
                return getDao().getById(id);
            }

            @Override
            protected void onPostExecute(Data data) {
                callback.onFetched(data);
            }
        }.execute();
    }

    public static void init(Context context) {
        Database database =
                Room.databaseBuilder(context, Database.class, DB_NAME)
                        .build();

        INSTANCE.dataDao = database.dao();
    }

    public static void getAllData(MediaType type, GetDataListCallback callback) {
        getAllData(type,SortType.NONE,callback);
    }

    public static void getAllData(MediaType mediaType, SortType sortType,
                                  GetDataListCallback callback) {

        new GetListTask(mediaType, sortType, getDao(), callback).execute();
    }

    public static void getFiltered(MediaType mediaType, String query,
                                   GetDataListCallback callback) {
        new GetFilteredTask(mediaType,getDao(),query,callback).execute();
    }

}
