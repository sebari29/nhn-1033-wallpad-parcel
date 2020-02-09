package com.wallpad.delivery.common;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

import com.blankj.utilcode.util.LogUtils;

public class APIContentProviderHelper {

    private final static String TAG = APIContentProviderHelper.class.getSimpleName();

    /**
     * Content URI
     */
    static final Uri API_CACHE_CONTENT_URI = Uri.parse("content://com.wallpad.net.provider.APICacheContentProvider/t_cache");

    static final String SELECTION = "functionID";

    static final String COL_API_RESPONSE = "apiResponse";

    private static final String FUNTIONID_NOTIFICATION  = "1F033001";

    final ContentResolver mContentResolver;

    /**
     * In case APICacheProvider does not exist, cache its state to avoid unwanted repeated tries
     */
    private static int sAPICacheState = Constant.INVALID_NUMBER;

    public APIContentProviderHelper(ContentResolver contentResolver) {
        if (contentResolver == null) {
            throw new IllegalArgumentException("ContentResolver must not be null");
        }
        mContentResolver = contentResolver;
    }

    /**
     * Get notification response from GSmart Provider
     */
    public String getNotificationResponse() {
        if (isAPIProviderSupported()) {
            return queryString(FUNTIONID_NOTIFICATION);
        }
        return Constant.STRING_EMPTY;
    }

    String queryString(String key) {
        String value = Constant.STRING_EMPTY;
        try (Cursor cursor = mContentResolver.query(API_CACHE_CONTENT_URI, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int columnIndex = cursor.getColumnIndex(COL_API_RESPONSE);
                    int columnSelection = cursor.getColumnIndex(SELECTION);
                    if (columnSelection != -1 && cursor.getString(columnSelection).equals(key)) {
                        value = cursor.getString(columnIndex);
                    }
                }while (cursor.moveToNext());

            }
        }
        return value;
    }

    /**
     * Check APICacheProvider is supported or not
     */
    private boolean isAPIProviderSupported() {
        if (sAPICacheState == Constant.INVALID_NUMBER) {
            sAPICacheState = checkAPICacheState();
        }
        return sAPICacheState == Constant.DATABASE_STATE_EXIST;
    }

    private int checkAPICacheState() {
        int sspState = Constant.DATABASE_STATE_NOT_EXIST;
        Cursor cursor = mContentResolver.query(API_CACHE_CONTENT_URI,
                null, null, null, null);
        if (cursor != null) {
            sspState = Constant.DATABASE_STATE_EXIST;
            cursor.close();
        }
        return sspState;
    }

    /**
     * Register a content observer to observer GSmart provider change
     *
     * @param contentObserver {@link ContentObserver} to register
     */
    public void registerContentObserver(ContentObserver contentObserver) {
        if (contentObserver != null && isAPIProviderSupported()) {
            mContentResolver.registerContentObserver(API_CACHE_CONTENT_URI, false, contentObserver);
        } else {
            LogUtils.w(TAG, "#registerContentObserver() - There is no observer or API cache provider is not available, do nothing");
        }
    }

    /**
     * Unregister a content observer
     *
     * @param contentObserver {@link ContentObserver} to unregister
     */
    public void unregisterContentObserver(ContentObserver contentObserver) {
        if (contentObserver != null && isAPIProviderSupported()) {
            mContentResolver.unregisterContentObserver(contentObserver);
        } else {
            LogUtils.w(TAG, "#registerContentObserver() - There is no observer or API cache provider is not available, do nothing");
        }
    }

    /**
     * Verify uri is valid or not
     *
     * @param uri
     * @return true if valid, otherwise false
     */
    public boolean validateDemoModeSonySettingUri(Uri uri) {
        return uri != null && (API_CACHE_CONTENT_URI.equals(uri));
    }
}
