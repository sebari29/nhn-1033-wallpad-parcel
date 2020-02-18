package com.wallpad.delivery.view.delivery;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wallpad.basemvvm.view.BaseFragment;
import com.wallpad.delivery.R;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.databinding.DeliveryFragmentBinding;
import com.wallpad.delivery.view.customview.loadmore.WrapContentLinearLayoutManager;
import com.wallpad.delivery.viewmodel.DeliveryViewModel;

import java.util.List;

import static com.wallpad.delivery.common.Constant.INTENT_ACTION_LOADMORE_PARCEL;
import static com.wallpad.delivery.common.Constant.INTENT_ACTION_NOTICE;

public class DeliveryFragment extends BaseFragment {


    private DeliveryFragmentBinding binding;

    private DeliveryViewModel mNoticeViewModel;

    private final static String TAG = DeliveryFragment.class.getSimpleName();

    public interface OnDataChangedListener {
        void onNotificationChanged(List<Delivery> notifications);
    }

    private final static String GSMART_SERVICE_CLASS_NAME = Constant.GSMART_PACKAGE_NAME + ".GSmartService";


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
        binding.noticeList.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        binding.btnClose.setOnClickListener(mOnClickListener);
        mNoticeViewModel.changeTxtAMorPM();
    }

    @Override
    protected void initData() {
        mNoticeViewModel.registerObserverDBChange();
        getAllNotices();
//        getAllMockNotices();
//        initScrollListener();
    }

    private void getAllNotices() {
        mNoticeViewModel.refreshData();
//        Message message = new Message();
//        message.obj = new ParcelEvent(mOnDataChangedListener);
//        mWorkerThread.sendMessage(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mUpdateTimeReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        getContext().registerReceiver(mNoticeViewModel.getBroadcastParcelNotify(), new IntentFilter(INTENT_ACTION_NOTICE));
        getContext().registerReceiver(mNoticeViewModel.getReceiverLoadmore(), new IntentFilter(INTENT_ACTION_LOADMORE_PARCEL));
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mUpdateTimeReceiver);
        getContext().unregisterReceiver(mNoticeViewModel.getBroadcastParcelNotify());
        getContext().unregisterReceiver(mNoticeViewModel.getReceiverLoadmore());
    }

    @Override
    public void onStart() {
        super.onStart();
        bindGSmartService();

    }

    private void bindGSmartService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Constant.GSMART_PACKAGE_NAME, GSMART_SERVICE_CLASS_NAME));
        getContext().bindService(intent, mNoticeViewModel.getmServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mNoticeViewModel.unbindGSmartService();
        getContext().unbindService(mNoticeViewModel.getmServiceConnection());
    }

    @Override
    public void onDestroy() {
        mNoticeViewModel.unregsiterObserverDBChange();
        super.onDestroy();

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
