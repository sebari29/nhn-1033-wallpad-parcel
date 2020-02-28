package com.wallpad.delivery;

import android.content.res.Configuration;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.basemvvm.view.BaseActivity;
import com.wallpad.delivery.view.delivery.DeliveryFragment;
import com.wallpad.dialog.ProgressDialogCustom;

import java.util.List;

public class MainActivity extends BaseActivity {
    private boolean mBounded;
    private final static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialogCustom dialogLoading;


    /**
     * call back client connection
     */


    @Override
    protected int initLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        dialogLoading = new ProgressDialogCustom(this);
        showLoading();
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

    public void hideLoading() {
        dialogLoading.hideDialog();
    }

    public void showLoading() {
        dialogLoading.showDialog();
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    private void start() {

    }


    @Override
    protected void onStart() {
        super.onStart();

      //  bindGSmartService();

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


}
