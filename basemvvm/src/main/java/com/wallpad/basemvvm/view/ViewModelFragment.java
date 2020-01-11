package com.wallpad.basemvvm.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelFragment extends Fragment {


    protected  <VM extends ViewModel> VM initViewModel(Class<VM> modelClass) {
        return ViewModelProviders.of(this).get(modelClass);
    }


    protected  <T extends ViewDataBinding> T bindingView(
            LayoutInflater inflater,
            int resId,
            ViewGroup container) {
        return DataBindingUtil.inflate(inflater, resId, container, false);

    }

}
