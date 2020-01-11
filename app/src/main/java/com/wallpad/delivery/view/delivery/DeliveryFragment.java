package com.wallpad.delivery.view.delivery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.basemvvm.view.BaseFragment;
import com.wallpad.delivery.MainActivity;
import com.wallpad.delivery.R;
import com.wallpad.delivery.databinding.DeliveryFragmentBinding;
import com.wallpad.delivery.viewmodel.DeliveryViewModel;

public class DeliveryFragment extends BaseFragment {

    private final static String TAG = MainActivity.class.getSimpleName();

    private DeliveryFragmentBinding binding;

    private DeliveryViewModel mNoticeViewModel;

    private DeliveryAdapter mNoticesAdapter;

    private BroadcastReceiver mUpdateTimeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.d(TAG, "onReceive() - ");
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
        getAllNotices();
        mNoticeViewModel.changeTxtAMorPM();
    }

    @Override
    protected void initData() {

    }

    private void getAllNotices() {
        mNoticeViewModel.getNotifyData().observe(this, notices -> mNoticesAdapter.setNoticeList(notices));
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
