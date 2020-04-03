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
import com.erank.applerssfeed.utils.interfaces.ListGetter;

import java.util.List;

public class DataSource {

    private static final DataSource INSTANCE = new DataSource();
    private static final String DB_NAME = "db_apple_data";

    private Dao dao;

    private DataSource() {
    }

    public static void getData(Context context, MediaType type,
                               DataFetchedCallback callback) {
        ResultsFetcher.getData(context, type, new DataFetchedCallback() {
            @Override
            public void onDataFetched(List<Data> list, MediaType type) {
                insertTask(list, type, callback);
            }

            @Override
            public void onErrorFetching(Exception e) {
                callback.onErrorFetching(e);
            }
        });
    }

    private static void insertTask(List<Data> list, MediaType type, DataFetchedCallback callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                getDao().insertDataList(list);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                callback.onDataFetched(list, type);
            }
        }.execute();
    }

    public static void getAllData(MediaType type, GetDataListCallback callback) {
        getList(() -> getDao().getAll(type), callback);
    }

    private static void getList(ListGetter listGetter, GetDataListCallback callback) {
        new AsyncTask<Void, Void, List<Data>>() {
            @Override
            protected List<Data> doInBackground(Void... voids) {
                return listGetter.getList();
            }

            @Override
            protected void onPostExecute(List<Data> list) {
                callback.onListFetched(list);
            }
        }.execute();
    }

    private static Dao getDao() {
        return INSTANCE.dao;
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
        Database database = Room.databaseBuilder(context, Database.class, DB_NAME)
                .build();

        INSTANCE.dao = database.dao();
    }

    public static void getAllData(MediaType mediaType, SortType sortType,
                                  GetDataListCallback callback) {
        ListGetter listGetter;
        switch (sortType) {
            case NAME:
                listGetter = () -> getDao().getAllOrderedByName(mediaType);
                break;
            case DATE:
                listGetter = () -> getDao().getAllOrderedByDate(mediaType);
                break;
            case GENRE:
                listGetter = () -> getDao().getAllOrderedByGenre(mediaType);
                break;

            default:
                callback.onListFetched(null);
                return;
        }
        getList(listGetter, callback);
    }

    public static void getFiltered(MediaType mediaType, String query,
                                   GetDataListCallback callback) {
        getList(() -> getDao().getFiltered(mediaType, query + "%"), callback);
    }

}
