package com.wallpad.delivery.application;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class AppController extends MultiDexApplication {
    private static AppController mInstance;
    public synchronized static AppController getInstance() {
        return mInstance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        MultiDex.install(this);
    }

}
