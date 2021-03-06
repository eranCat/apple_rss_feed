package com.erank.applerssfeed.utils.room;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;

import java.util.List;

@androidx.room.Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDataList(List<Data> list);

    @Query("SELECT * FROM Data WHERE type = :type")
    List<Data> getAll(MediaType type);

    @Query("SELECT * FROM Data WHERE type = :type ORDER BY name")
    List<Data> getAllOrderedByName(MediaType type);

    @Query("SELECT * FROM Data WHERE type = :type ORDER BY release_date,name DESC")
    List<Data> getAllOrderedByDate(MediaType type);

    @Query("SELECT * FROM Data WHERE type = :type ORDER BY genres")
    List<Data> getAllOrderedByGenre(MediaType type);

    @Query("SELECT * FROM Data WHERE type = :type AND name LIKE '%'+:query+'%'")
    List<Data> getFiltered(MediaType type, String query);

    @Query("SELECT * FROM Data WHERE id = :id")
    Data getById(String id);
}