package com.erank.applerssfeed.utils.room;


import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.erank.applerssfeed.models.Data;

@androidx.room.Database(entities = {Data.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract Dao dao();
}