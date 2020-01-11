package com.wallpad.delivery.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.wallpad.delivery.data.model.Delivery;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QueriesDao {
    @Insert(onConflict = REPLACE)
    void addNewNotification(Delivery Notice);

    @Query("SELECT * FROM t_delivery")
    LiveData<List<Delivery>> getAllNotifications();


}