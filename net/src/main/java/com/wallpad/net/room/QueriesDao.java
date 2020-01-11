package com.wallpad.net.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QueriesDao {
    @Query("SELECT * FROM t_cache")
    List<ApiCache> getAllListCache();

    @Query("SELECT apiResponse FROM t_cache WHERE apiRequest LIKE :apiRequest")
    String getCacheFromApiRequest(String apiRequest);

    @Insert(onConflict = REPLACE)
    void insertCache(ApiCache api);
}
