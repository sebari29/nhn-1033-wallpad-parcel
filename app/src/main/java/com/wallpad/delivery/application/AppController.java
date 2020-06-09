package com.wallpad.delivery.application;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.common.TypefaceUtil;


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
        TypefaceUtil.overrideFont(getApplicationContext(), Constant.TYPE_SERIF, Constant.NAME_FILE_SERIF);//regular
        TypefaceUtil.overrideFont(getApplicationContext(), Constant.TYPE_DEFAULT, Constant.NAME_FILE_DEFAULT);//medium
        TypefaceUtil.overrideFont(getApplicationContext(), Constant.TYPE_MONOSPACE, Constant.NAME_FILE_MONOSPACE);//light
    }

}
