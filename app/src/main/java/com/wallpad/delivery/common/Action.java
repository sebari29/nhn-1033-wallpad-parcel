package com.wallpad.delivery.common;

import android.os.Bundle;

public class Action {
    public static final int ACTION_START_ACTIVITY = 1;
    public static final int ACTION_SEND_BROADCAST = 2;
    public static final int ACTION_NONE = 0;

    int action;
    Object param;
    Bundle bundle;
    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Action(int action) {
        this.action = action;
    }

    public Action(int action, Object param) {
        this.action = action;
        this.param = param;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
