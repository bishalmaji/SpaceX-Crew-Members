package com.bishal.datafrominternet.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.bishal.datafrominternet.model.CrewModel;

import java.util.List;

@Dao
public interface CrewDao {

    @Query("SELECT * FROM crewtable")
    List<CrewModel> getAllUsers();

    @Insert
    void insertUser(List<CrewModel> users);

    @Query("DELETE FROM crewtable")
    void delete();

}