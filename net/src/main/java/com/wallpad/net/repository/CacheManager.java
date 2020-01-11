package com.wallpad.net.repository;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wallpad.net.room.ApiCache;
import com.wallpad.net.room.AppDatabase;
import com.wallpad.net.room.QueriesDao;
/*

 */
public class CacheManager {
    private final QueriesDao queryDao;
    private Context mContext;

    public CacheManager(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mContext = application.getApplicationContext();
        queryDao = db.queries();
    }

    public String getDataFromLink(String request, boolean isSaveToCache) {
        String result = null;
        if (!isOnline(mContext)) {
            result = getCacheFromDisk(request);
        } else {
            // using socket update db and update ui
            if (isSaveToCache) {
                ApiCache apiCache = new ApiCache();
                insertCache(apiCache);
            }

        }
        return result;
    }

    public String getCacheFromDisk(String apiRequest) {
        return queryDao.getCacheFromApiRequest(apiRequest);
    }

    void insertCache(ApiCache apiCache) {
        queryDao.insertCache(apiCache);
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }
}
