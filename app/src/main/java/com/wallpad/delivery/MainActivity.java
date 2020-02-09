package com.wallpad.delivery;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.gsmart.IGSmartData;
import com.gsmart.IGSmartDataCallback;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.event.ParcelEvent;
import com.wallpad.delivery.repository.GSmartWorkerThread;
import com.wallpad.delivery.view.delivery.DeliveryAdapter;
import com.wallpad.net.socket.SocketClient;
import com.wallpad.net.socket.TcpClient;
import com.wallpad.basemvvm.view.BaseActivity;
import com.wallpad.delivery.view.delivery.DeliveryFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TcpClient mTcpClient;
    protected SocketClient mSocket;
    private boolean mBounded;
    private final static String TAG = MainActivity.class.getSimpleName();

    public interface OnDataChangedListener {
        void onNotificationChanged(List<Delivery> notifications);
    }

    private final static String GSMART_SERVICE_CLASS_NAME = Constant.GSMART_PACKAGE_NAME + ".GSmartService";

    private boolean mGSmartServiceBound;

    private IGSmartData mIGSmartData;

    private RefreshHandler mRefreshHandler;

    private final Handler mMainHandler = new Handler();

    private APIContentProviderHelper mApiContentProviderHelper;

    private GSmartWorkerThread mWorkerThread;

    private final OnDataChangedListener mOnDataChangedListener = new OnDataChangedListener() {
        @Override
        public void onNotificationChanged(List<Delivery> notifications) {
            LogUtils.d(TAG, "onNotificationChanged() - ");
//            mNoticesAdapter.setNoticeList(notifications);
        }
    };

    private final IGSmartDataCallback.Stub mIGSmartDataCallback = new IGSmartDataCallback.Stub() {
        @Override
        public void onNotificationLoadCompleted(String notifications) throws RemoteException {
            LogUtils.d(TAG, "onNotificationLoadCompleted() - " + notifications);
            LogUtils.d(TAG, "onNotificationLoadCompleted() - " + Looper.getMainLooper().equals(Looper.myLooper()));
            Message message = new Message();
            message.obj = notifications;
            mRefreshHandler.sendMessage(message);
        }
    };

    /**
     * call back client connection
     */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            LogUtils.d(TAG, "onServiceConnected method is called");
            mGSmartServiceBound = true;
            mIGSmartData = IGSmartData.Stub.asInterface(service);
            try {
                mIGSmartData.addClientListener(mIGSmartDataCallback);
            } catch (RemoteException e) {
                LogUtils.e(TAG, "#onServiceConnected " + e.getMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            LogUtils.d(TAG, "onServiceDisconnected method is called");
            try {
                mIGSmartData.removeClientListener(mIGSmartDataCallback);
            } catch (RemoteException e) {
                LogUtils.e(TAG, "#onServiceConnected " + e.getMessage());
            }
            mGSmartServiceBound = false;
        }
    };
    private final ContentObserver mContentObserver = new ContentObserver(mMainHandler) {
        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            LogUtils.d(TAG, "mContentObserver - onChange - selfChange = " + selfChange);
            super.onChange(selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            LogUtils.d(TAG, "mContentObserver - onChange - selfChange = " + selfChange + ", uri = " + uri.toString());
            if (mApiContentProviderHelper.validateDemoModeSonySettingUri(uri)) {
                getAllNotices();
            }
            super.onChange(selfChange, uri);
        }
    };

    private final GSmartWorkerThread.WorkerThreadListener mWorkerThreadListener = new GSmartWorkerThread.WorkerThreadListener() {
        @Override
        public void onTimeout() {
            mWorkerThread.quit();
            LogUtils.d(TAG, "onTimeout - ");
        }
    };


    @Override
    protected void adjustDisplayScale() {
        Configuration configuration = getResources().getConfiguration();
        if (configuration != null) {
            int densityDpi = getResources().getDisplayMetrics().densityDpi;
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            int screenLayoutSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

            switch (screenLayoutSize) {
                case Configuration.SCREENLAYOUT_SIZE_SMALL:
                    // TODO: Handle small size
//                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
//                        str = "small-xxhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
//                        str = "small-xxhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
//                        str = "small-xhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
//                        str = "small-hdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
//                        str = "small-tvdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
//                        str = "small-mdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
//                        str = "small-low";
//                    }
                    break;
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                        configuration.densityDpi = 320;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                        if (configuration.densityDpi >= 480) {
                            if (getResources().getDisplayMetrics().widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (getResources().getDisplayMetrics().widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else if (getResources().getDisplayMetrics().widthPixels >= 1900)
                                configuration.densityDpi = 320;
                            else
                                configuration.densityDpi = 300;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 426)
                            configuration.densityDpi = 300;
                        else
                            configuration.densityDpi = 240;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 284)
                            configuration.densityDpi = 200;
                        else
                            configuration.densityDpi = 160; // mdpi_xlarge
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        // TODO: Handle TV size if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        // TODO: Handle density medium  if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        // TODO: Handle density low  if need
                    }
                    break;
                case Configuration.SCREENLAYOUT_SIZE_LARGE:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                        if (configuration.densityDpi >= 480)
                            configuration.densityDpi = 400;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 426) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else configuration.densityDpi = 320;
                        } else if (configuration.densityDpi >= 372) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else
                                configuration.densityDpi = 300;
                        } else if (configuration.densityDpi >= 300) {
                            configuration.densityDpi = 300;
                        } else {
                            configuration.densityDpi = 240;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 272)
                            configuration.densityDpi = 300;
                        else if (configuration.densityDpi >= 248)
                            configuration.densityDpi = 200;
                        else if (configuration.densityDpi >= 240)
                            configuration.densityDpi = 300;
                        else
                            configuration.densityDpi = 320;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        configuration.densityDpi = 200;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        if (configuration.densityDpi >= 180)
                            configuration.densityDpi = 200;
                        else
                            configuration.densityDpi = 160;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        configuration.densityDpi = 160;
                    }

                    break;
                case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 320) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else
                                configuration.densityDpi = 320;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 272) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else
                                configuration.densityDpi = 320;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        // TODO: Handle TV size if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        // TODO: Handle density medium  if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        configuration.densityDpi = 240;
                    }
                    break;

                default:
                    break;
            }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            float systemScale = Settings.System.getFloat(getApplicationContext().getContentResolver(), Settings.System.FONT_SCALE, 1f);
            if (systemScale == 0.85f) {
                configuration.fontScale = 0.95f;
            } else if (systemScale == 1f) {
                configuration.fontScale = 1f;
            } else if (systemScale == 1.15f) {
                configuration.fontScale = 1.05f;
            } else if (systemScale == 1.3f) {
                configuration.fontScale = 1.1f;
            }
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            if (display != null) {
                display.getMetrics(metrics);
            }
            this.getResources().updateConfiguration(configuration, null);
        }
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );
        fragmentTransaction.replace(R.id.main_frame_root, DeliveryFragment.newInstance());
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    private void start() {

    }
    private void getAllNotices() {
        Message message = new Message();
        message.obj = new ParcelEvent(mOnDataChangedListener);
        mWorkerThread.sendMessage(message);
    }

    @Override
    protected void onStart() {
        super.onStart();

      //  bindGSmartService();

    }

    private void bindGSmartService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Constant.GSMART_PACKAGE_NAME, GSMART_SERVICE_CLASS_NAME));
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindGSmartService() {
        try {
            mIGSmartData.removeClientListener(mIGSmartDataCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(mServiceConnection);
        mIGSmartData = null;
    }
    @Override
    protected void onStop() {
        super.onStop();
       // unbindGSmartService();

    }

    @Override
    protected void onDestroy() {
//        mApiContentProviderHelper.unregisterContentObserver(mContentObserver);;
//        mWorkerThread.quit();
        super.onDestroy();

    }




    private static class RefreshHandler extends Handler {

        private final WeakReference<DeliveryAdapter> mWeakHandler;

        /**
         * Constructor
         */
        private RefreshHandler(Looper looper, DeliveryAdapter mediaHandler) {
            super(looper);
            mWeakHandler = new WeakReference<>(mediaHandler);
        }

        @Override
        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MSG_REQ_REFRESH_UI:
            DeliveryAdapter noticesAdapter = mWeakHandler.get();
            if (noticesAdapter != null) {
                noticesAdapter.setNoticeList((List<Delivery>) msg.obj);
            }
//                    break;
//                default:
//                    break;
//        }
        }
    }

}
