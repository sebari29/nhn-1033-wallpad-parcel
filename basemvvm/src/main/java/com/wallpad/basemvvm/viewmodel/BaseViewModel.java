package com.wallpad.basemvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public BaseViewModel() {
        isLoading.setValue(false);
    }

    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void hideLoading() {
        isLoading.postValue(false);
    }

    public void showLoading() {
        isLoading.postValue(true);
    }
}
