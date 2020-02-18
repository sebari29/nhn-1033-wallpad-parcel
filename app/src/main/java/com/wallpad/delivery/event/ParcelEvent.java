package com.wallpad.delivery.event;

import android.content.Intent;

import com.wallpad.delivery.MainActivity;


public class ParcelEvent extends BaseGSmartEvent {

    public ParcelEvent(MainActivity.OnDataChangedListener callback) {
        super(callback);
    }

    @Override
    public void setData(Intent intent) {
        // No need implement at this time
    }
}
