package com.wallpad.delivery.view.delivery;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.gsmart.IGSmartData;
import com.gsmart.IGSmartDataCallback;
import com.wallpad.basemvvm.view.BaseFragment;
import com.wallpad.delivery.MainActivity;
import com.wallpad.delivery.R;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.databinding.DeliveryFragmentBinding;
import com.wallpad.delivery.event.ParcelEvent;
import com.wallpad.delivery.repository.GSmartWorkerThread;
import com.wallpad.delivery.viewmodel.DeliveryViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class DeliveryFragment extends BaseFragment {


    private DeliveryFragmentBinding binding;

    private DeliveryViewModel mNoticeViewModel;

    private DeliveryAdapter mNoticesAdapter;
    private final static String TAG = DeliveryFragment.class.getSimpleName();

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

    private final MainActivity.OnDataChangedListener mOnDataChangedListener = new MainActivity.OnDataChangedListener() {
        @Override
        public void onNotificationChanged(List<Delivery> notifications) {
            LogUtils.d(TAG, "onNotificationChanged() - ");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mNoticesAdapter.setNoticeList(notifications);
                }
            });
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
            try {
                mIGSmartData.refreshDeliveryInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
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

    private BroadcastReceiver mUpdateTimeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            LogUtils.d(TAG, "onReceive() - ");
            mNoticeViewModel.changeTxtAMorPM();
        }
    };

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };

    public static DeliveryFragment newInstance() {

        Bundle args = new Bundle();

        DeliveryFragment fragment = new DeliveryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.delivery_fragment;
    }


    @Override
    protected void initAction() {

    }

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = bindingView(inflater, initLayoutRes(), container);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mNoticeViewModel = initViewModel(DeliveryViewModel.class);
        binding.setViewmodel(mNoticeViewModel);
        binding.setLifecycleOwner(this);
        binding.noticeList.setHasFixedSize(true);
        binding.noticeList.setLayoutManager(new LinearLayoutManager(getContext()));
        mNoticesAdapter = new DeliveryAdapter(getContext());
        binding.noticeList.setAdapter(mNoticesAdapter);
        binding.btnClose.setOnClickListener(mOnClickListener);
//        getAllNotices();
        mNoticeViewModel.changeTxtAMorPM();
    }

    @Override
    protected void initData() {
        mWorkerThread = new GSmartWorkerThread(getContext(), DeliveryFragment.class.getName(), mWorkerThreadListener);
        mWorkerThread.start();
        mWorkerThread.prepareHandler();
        mApiContentProviderHelper = new APIContentProviderHelper(getContext().getContentResolver());
        mRefreshHandler = new RefreshHandler(Looper.getMainLooper(), mNoticesAdapter);
        mApiContentProviderHelper.registerContentObserver(mContentObserver);
        getAllNotices();
    }

//    private void getAllNotices() {
//        mNoticeViewModel.getDeliveryData().observe(this, notices -> mNoticesAdapter.setNoticeList(notices));
//    }

    private void getAllNotices() {
        Message message = new Message();
        message.obj = new ParcelEvent(mOnDataChangedListener);
        mWorkerThread.sendMessage(message);

    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mUpdateTimeReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mUpdateTimeReceiver);

    }

    @Override
    public void onStart() {
        super.onStart();
        bindGSmartService();

    }

    private void bindGSmartService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Constant.GSMART_PACKAGE_NAME, GSMART_SERVICE_CLASS_NAME));
        getContext().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindGSmartService() {
        try {
            mIGSmartData.removeClientListener(mIGSmartDataCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        getContext().unbindService(mServiceConnection);
        mIGSmartData = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbindGSmartService();

    }

    @Override
    public void onDestroy() {
        mApiContentProviderHelper.unregisterContentObserver(mContentObserver);
        ;
        mWorkerThread.quit();
        super.onDestroy();

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
