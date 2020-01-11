package com.wallpad.net.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_cache")
public class ApiCache {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "apiRequest")
    private String apiRequest;

    @ColumnInfo(name = "apiResponse")
    private String apiResponse;

    public String getApiRequest() {
        return apiRequest;
    }

    public void setApiRequest(@NonNull String apiRequest) {
        this.apiRequest = apiRequest;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }
}
