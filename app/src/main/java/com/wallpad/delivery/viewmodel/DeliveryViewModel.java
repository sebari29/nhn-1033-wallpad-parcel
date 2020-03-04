package com.wallpad.delivery.viewmodel;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.LogUtils;
import com.gsmart.IGSmartData;
import com.gsmart.IGSmartDataCallback;
import com.wallpad.basemvvm.viewmodel.BaseAndroidViewModel;
import com.wallpad.delivery.broadcast.ReceiveParcelNotify;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.common.DateTimeUtils;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.repository.DeliveryRepository;
import com.wallpad.delivery.view.customview.loadmore.ILoadmore;
import com.wallpad.delivery.view.delivery.DeliveryAdapter;
import com.wallpad.delivery.view.delivery.DeliveryFragment;
import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.parcelInfoInquiry.response.Data;
import com.wallpad.net.sample.parcelInfoInquiry.response.HNMLInquiryResponse;
import com.wallpad.net.sample.parcelInfoInquiry.response.Output;
import com.wallpad.net.sample.parcelnotification.request.HNMLParcelNotification;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/**
 * {@link DeliveryViewModel}
 */
public class DeliveryViewModel extends BaseAndroidViewModel {

    private final DeliveryRepository mRepository;
    private final Handler mMainHandler = new Handler();
    private final static String TAG = DeliveryViewModel.class.getSimpleName();
    private DeliveryAdapter adapter;
    public final MutableLiveData<String> txtAMorPM = new MutableLiveData<>();
    public final MutableLiveData<String> txtTime = new MutableLiveData<>();
    private APIContentProviderHelper mApiContentProviderHelper;
    private MutableLiveData<Boolean> isLoadingmore = new MutableLiveData<>();
    private MutableLiveData<Integer> scrollToTop = new MutableLiveData<>();
    private MutableLiveData<Boolean> hasNoMoreItem = new MutableLiveData<>();
    private boolean mGSmartServiceBound;
    private IGSmartData mIGSmartData;
    private int indexPage = 0;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    //implement loading progress bar
    @Override
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    private BroadcastReceiver receiverLoading = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.d("BroadcastReceiver Loading ");
            if (intent.getBundleExtra(Constant.BUNDLE) != null) {
                if (intent.getBundleExtra(Constant.BUNDLE).getBoolean(Constant.MSG))
                    isLoading.postValue(intent.getBundleExtra(Constant.BUNDLE).getBoolean(Constant.MSG));
            }
        }
    };

    /**
     * Callback loadmore.
     * add loadmoreview to adapter
     * send request loadmore to server
     */
    public ILoadmore iLoadmore = new ILoadmore() {
        @Override
        public void onLoadmore() {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    adapter.addLoadmoreView();

                }
            });
            LogUtils.d("add loadmoreview ...");
            try {
                if (mIGSmartData != null) {
                    indexPage++;
                    mIGSmartData.refreshDeliveryInfo(indexPage);
                    LogUtils.d("-------------------> loadmore now ..." + indexPage);
                }
            } catch (Exception e) {
                e.printStackTrace();
                indexPage--;
            }
        }
    };

    /**
     * this callback for GsmartService interact to client.
     * This is one approach , we're using broadcast to interact client instead of this.
     */
    private final IGSmartDataCallback.Stub mIGSmartDataCallback = new IGSmartDataCallback.Stub() {
        @Override
        public void onNotificationLoadCompleted(String notifications) throws RemoteException {

        }
    };
    /**
     * call back client connection
     * When client connected to GsmartService. this Callback will be fired. We should save instance of service to use after.
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
                LogUtils.d("call requsest to gsmart service refresh data  now");
                mIGSmartData.refreshDeliveryInfo(indexPage);
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

    /**
     * Observer when database change. Trigger will call when ContentProvider relize that DB change data.
     * Note : this observer will change whenever DB change row. So will must check that the changed row is exactly which we using in app.
     * You currentData variable to save old data then check if difference with currentData to refresh View.
     */
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
                refreshData();
            }
            super.onChange(selfChange, uri);
        }
    };

    /**
     * Broadcast fired when new notification from server
     * We will add new item to list data at 0 position
     */
    private ReceiveParcelNotify broadcastParcelNotify = new ReceiveParcelNotify(new ReceiveParcelNotify.IReceive() {
        @Override
        public void onReceived(String data) {
            LogUtils.d("onReceived at view : " + data);
            Delivery delivery = mRepository.getDeliveryFrom(data);
            if (delivery != null) {
                adapter.getmListData().add(0, delivery);
                adapter.notifyItemInserted(0);
                mApiContentProviderHelper.updateVisibleGone();
                scrollToTop.postValue(0);
                if (adapter.getItemCount() == Constant.MAX_SIZE_LIST) {
                    adapter.notifyItemChanged(Constant.MAX_SIZE_LIST - 1);
                    noMoreItem();
                }
            }
        }
    });
    /**
     * Broadcast fired when receive trigger load more from GsmartService.
     * We will add new items to list and finish loadmore
     */
    private BroadcastReceiver receiverLoadmore = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.d("receiverLoadmore");
            setLoaded();
            String dataStr = intent.getBundleExtra(Constant.BUNDLE).getString(Constant.MSG);
            if (dataStr != null) {
                List<Delivery> deliveries = mRepository.getListDeliveriesFrom(dataStr);
                if (deliveries != null && !deliveries.isEmpty()) {
                    adapter.removeLoadmore();
                    adapter.addAll(deliveries);
                    isLoadingmore.postValue(false);
                    if (adapter.getmListData().size() > Constant.MAX_SIZE_LIST)
                        noMoreItem(); // list max is 128 item. no show more
                } else {
                    noMoreItem();
                    adapter.removeLoadmore();
                    isLoadingmore.postValue(false);

                }
            } else {
                noMoreItem();
            }
        }
    };

    /**
     * use for notice recycler that has no more item
     */
    private void noMoreItem() {
        isLoadingmore.postValue(false);
        hasNoMoreItem.postValue(true);
    }

    /**
     * Reload all list data
     */
    public void refreshData() {
        List<Delivery> list = mRepository.getDeliveryData(mApiContentProviderHelper, new DeliveryRepository.ICallBack() {
            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void onCallBack(List<Delivery> list) {

            }
        });
        adapter.setNoticeList(list);
        isLoadingmore.postValue(false);
        hasNoMoreItem.postValue(false);
    }

    /**
     * Constructor
     *
     * @param application {@link Application}
     */
    public DeliveryViewModel(Application application) {
        super(application);
        mRepository = new DeliveryRepository(application);
        mApiContentProviderHelper = new APIContentProviderHelper(application.getApplicationContext().getContentResolver());
        adapter = new DeliveryAdapter();
        isLoadingmore.setValue(true);
        hasNoMoreItem.setValue(false);
        isLoading.setValue(false);
        scrollToTop.setValue(0);
    }

    public void setLoaded() {
        isLoadingmore.postValue(false);
    }

    /**
     * Change time info to AM or PM
     */
    public void changeTxtAMorPM(Date date) {
        txtAMorPM.postValue(new DateTimeUtils().getAMorPMTime(date));
        txtTime.postValue(new DateTimeUtils().get12hourTime(date));
    }

    public DeliveryAdapter getAdapter() {
        return adapter;
    }


    private void refreshList() {
        indexPage = 0;
        if (mIGSmartData != null) {
            try {
                mIGSmartData.refreshDeliveryInfo(indexPage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    public void unbindGSmartService() {
        if (mIGSmartData != null) {
            try {
                mIGSmartData.removeClientListener(mIGSmartDataCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mIGSmartData = null;
    }

    public void registerObserverDBChange() {
        mApiContentProviderHelper.registerContentObserver(mContentObserver);
    }

    public void unregsiterObserverDBChange() {
        mApiContentProviderHelper.unregisterContentObserver(mContentObserver);
    }


    public MutableLiveData<Boolean> getIsLoadingmore() {
        return isLoadingmore;
    }

    public void setIsLoadingmore(MutableLiveData<Boolean> isLoadingmore) {
        this.isLoadingmore = isLoadingmore;
    }

    public MutableLiveData<Boolean> getHasNoMoreItem() {
        return hasNoMoreItem;
    }

    public ServiceConnection getmServiceConnection() {
        return mServiceConnection;
    }

    public void setHasNoMoreItem(MutableLiveData<Boolean> hasNoMoreItem) {
        this.hasNoMoreItem = hasNoMoreItem;
    }

    public ILoadmore getiLoadmore() {
        return iLoadmore;
    }

    public void setiLoadmore(ILoadmore iLoadmore) {
        this.iLoadmore = iLoadmore;
    }

    public ReceiveParcelNotify getBroadcastParcelNotify() {
        return broadcastParcelNotify;
    }

    public MutableLiveData<Integer> getScrollToTop() {
        return scrollToTop;
    }

    public void setScrollToTop(MutableLiveData<Integer> scrollToTop) {
        this.scrollToTop = scrollToTop;
    }

    public void setBroadcastParcelNotify(ReceiveParcelNotify broadcastParcelNotify) {
        this.broadcastParcelNotify = broadcastParcelNotify;
    }

    public BroadcastReceiver getReceiverLoadmore() {
        return receiverLoadmore;
    }

    public void setReceiverLoadmore(BroadcastReceiver receiverLoadmore) {
        this.receiverLoadmore = receiverLoadmore;
    }

    public BroadcastReceiver getReceiverLoading() {
        return receiverLoading;
    }
}
