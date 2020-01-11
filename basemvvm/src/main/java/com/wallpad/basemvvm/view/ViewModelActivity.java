package com.wallpad.basemvvm.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected <VM extends ViewModel> VM initViewModel(Class<VM> modelClass) {
        return ViewModelProviders.of(this).get(modelClass);
    }


    protected <T extends ViewDataBinding> T bindingView(int resId) {
        return DataBindingUtil.setContentView(this, resId);
    }

}
