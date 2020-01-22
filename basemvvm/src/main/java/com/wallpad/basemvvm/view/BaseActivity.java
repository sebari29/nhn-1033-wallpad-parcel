package com.wallpad.basemvvm.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressLint("Registered")
public abstract class BaseActivity extends ViewModelActivity {
    protected abstract int initLayoutRes();

    protected abstract void initView();

    protected abstract void initAction();

    protected abstract void initData();

    protected abstract void adjustDisplayScale();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adjustDisplayScale();
        setContentView(initLayoutRes());
        initView();
        initData();
        initAction();
    }

    protected void addFragment(Fragment fragment, int resID) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
            fragmentTransaction.add(resID, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void replaceFragment(Fragment fragment, int resID) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
            fragmentTransaction.replace(resID, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }


    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
