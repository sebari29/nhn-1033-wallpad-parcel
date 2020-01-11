package com.wallpad.delivery.view.login;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.basemvvm.viewmodel.BaseViewModel;
import com.wallpad.delivery.common.Action;

public class LoginViewmodel extends BaseViewModel {
    MutableLiveData<Action> action = new MutableLiveData<>();
    MutableLiveData<String> username = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();

    public LoginViewmodel() {
        action.setValue(new Action(Action.ACTION_NONE));
        username.setValue("");
        password.setValue("");
    }

    public MutableLiveData<Action> getAction() {
        return action;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void loginWith(String username,String password) {
        LogUtils.d("username : " + username + " password : " + password);
        showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                action.postValue(new Action(Action.ACTION_START_ACTIVITY));
            }
        }, 2000);
    }
}
