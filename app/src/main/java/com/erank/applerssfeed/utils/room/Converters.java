package com.erank.applerssfeed.utils.room;

import androidx.room.TypeConverter;

import com.erank.applerssfeed.models.Genre;
import com.erank.applerssfeed.models.MediaType;
import com.google.gson.Gson;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String stringArrToJson(Genre[] value) {
        return new Gson().toJson(value);
    }

    @TypeConverter
    public static Genre[] jsonArr(String value) {
        return new Gson().fromJson(value, Genre[].class);
    }

    @TypeConverter
    public static String enumToString(MediaType e) {
        return e.name();
    }

    @TypeConverter
    public static MediaType stringToEnum(String name) {
        return MediaType.valueOf(name);
    }
}