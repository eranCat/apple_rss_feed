package com.erank.applerssfeed.utils.room;

import android.content.Context;

import androidx.room.Room;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.utils.DataFetchedCallback;
import com.erank.applerssfeed.utils.Media;
import com.erank.applerssfeed.utils.ResultsFetcher;
import com.erank.applerssfeed.utils.SortType;

import java.util.List;

public class DataSource {

    private static final DataSource INSTANCE = new DataSource();
    private static final String DB_NAME = "db_apple_data";

    private Dao dao;

    private DataSource() {
    }

    public static void getData(Context context, Media type,
                               DataFetchedCallback callback) {
        ResultsFetcher.getData(context, type, new DataFetchedCallback() {
            @Override
            public void onDataFetched(List<Data> list, Media type) {
                callback.onDataFetched(list, type);
                getDao().insertDataList(list);
            }

            @Override
            public void onErrorFetching(Exception e) {
                callback.onErrorFetching(e);
            }
        });
    }

    public static List<Data> getAllData(Media type) {
        return getDao().getAll(type);
    }

    private static Dao getDao() {
        return INSTANCE.dao;
    }

    public static Data getData(String id) {
        return getDao().getById(id);
    }

    public static void init(Context context) {
        Database database = Room
                .databaseBuilder(context, Database.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

        INSTANCE.dao = database.dao();
    }

    public static List<Data> getAllData(Media media, SortType sortType) {

        switch (sortType) {
            case NAME:
                return getDao().getAllOrderedByName(media);
            case DATE:
                return getDao().getAllOrderedByDate(media);
            case GENRE:
                return getDao().getAllOrderedByGenre(media);
        }

        return null;
    }

    public static List<Data> getFiltered(Media media, String query) {
        return getDao().getFiltered(media, query);
    }
}
