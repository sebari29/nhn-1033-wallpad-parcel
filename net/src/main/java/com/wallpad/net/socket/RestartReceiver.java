package com.wallpad.net.socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;

/**
 * Created by Duong on 8/28/2017.
 */

public class RestartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!NetworkUtils.isConnected()) {
            LogUtils.e("No internet or log out");
            return;

        }
        LogUtils.e("onReceive RestartReceiver");
        context.startService(new Intent(context, SocketClient.class));
//         ref : http://fabcirablog.weebly.com/blog/creating-a-never-ending-background-service-in-android
//        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(SocketClient.TAG);
//        PendingIntent pendingIntent = PendingIntent.getService(context, (int) (System.currentTimeMillis()%10000),i,PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+100,5000,pendingIntent);

    }
}
