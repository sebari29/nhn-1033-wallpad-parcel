package com.wallpad.delivery.event;

import android.content.Intent;


import com.wallpad.delivery.MainActivity;

import java.io.Serializable;

public abstract class BaseGSmartEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private MainActivity.OnDataChangedListener mOnDataChangedListener;

    BaseGSmartEvent(MainActivity.OnDataChangedListener callback) {
        mOnDataChangedListener = callback;
    }

    public MainActivity.OnDataChangedListener getOnDataChangedListener() {
        return mOnDataChangedListener;
    }

    public abstract void setData(Intent intent);

}
