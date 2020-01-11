package com.wallpad.basemvvm.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseAndroidViewModel extends AndroidViewModel {
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


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
    public BaseAndroidViewModel(Application application) {
        super(application);
        isLoading.setValue(false);

    }


}
