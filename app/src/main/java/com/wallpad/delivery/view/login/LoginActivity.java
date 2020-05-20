package com.wallpad.delivery.view.login;

import android.content.Intent;

import androidx.lifecycle.Observer;

import com.wallpad.basemvvm.view.BaseActivity;
import com.wallpad.delivery.MainActivity;
import com.wallpad.delivery.R;
import com.wallpad.delivery.common.Action;
import com.wallpad.delivery.databinding.LoginActivityBinding;

public class LoginActivity extends BaseActivity {
    LoginActivityBinding binding;
    LoginViewmodel viewmodel;

    @Override
    protected int initLayoutRes() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        binding = bindingView(initLayoutRes());
        viewmodel = initViewModel(LoginViewmodel.class);
        binding.setViewmodel(viewmodel);
        binding.setView(this);
        binding.setLifecycleOwner(this);

    }

    @Override
    protected void initAction() {
        viewmodel.getAction().observe(this, new Observer<Action>() {
            @Override
            public void onChanged(Action action) {
                if (action.getAction() == Action.ACTION_START_ACTIVITY) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    public void onClickLogin() {
        viewmodel.loginWith(binding.loginEdtUsername.getText().toString(), binding.loginEdtPass.getText().toString());
    }

    @Override
    protected void initData() {

    }
}
