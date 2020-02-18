package com.wallpad.delivery.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.common.Constant;

public class ReceiveParcelNotify extends BroadcastReceiver {
    IReceive iReceive;

    public ReceiveParcelNotify(IReceive iReceive) {
        this.iReceive = iReceive;
    }

    public ReceiveParcelNotify() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getBundleExtra(Constant.BUNDLE) != null) {
            String data = intent.getBundleExtra(Constant.BUNDLE).getString(Constant.MSG);
            LogUtils.d("We receive data = " + data);
            if(iReceive!=null)
            iReceive.onReceived(data);
        }
    }

    public interface IReceive {
        void onReceived(String data);
    }
}
