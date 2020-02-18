/*
 * Copyright 2017 Sony Corporation
 * Copyright 2018 Sony Visual Products Inc.
 */
package com.wallpad.delivery.repository;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;

public final class GSmartWorkerThread extends HandlerThread {

    private static final String TAG = GSmartWorkerThread.class.getSimpleName();

    public interface WorkerThreadListener {
        void onTimeout();
    }

    private WorkerHandler mWorkerHandler;
    private final Context mContext;
    private final WorkerThreadListener mWorkerThreadListener;

    private final WorkerHandler.MessageWaitingTimeoutListener mListener = new WorkerHandler.MessageWaitingTimeoutListener() {
        @Override
        public void onTimeout() {
            handleTimeout();
        }
    };

    public GSmartWorkerThread(Context context, String name, WorkerThreadListener listener) {
        super(name);
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        mContext = context;
        mWorkerThreadListener = listener;
    }

    /**
     * Prepare Handler. Make sure this method is called after looper of this thread is prepared.
     */
    public void prepareHandler() {
        LogUtils.d(TAG, "prepareHandler");
        if (isAlive()) {
            Looper looper = getLooper();
            if (looper != null) {
                mWorkerHandler = new WorkerHandler(mContext, looper, mListener);
            } else {
                LogUtils.w(TAG, "#prepareHandler - Looper is null");
            }
        }
    }

    public void sendMessage(Message msg) {
        if (mWorkerHandler != null) {
            mWorkerHandler.clearGetDataMessage();
            mWorkerHandler.sendMessage(msg);
        } else {
            LogUtils.w(TAG, "#sendMessage - mWorkerHandler is null");
        }
    }

    public void sendGetDataMessage() {
        if (mWorkerHandler != null) {
            mWorkerHandler.clearGetDataMessage();
            mWorkerHandler.sendGetDataMessage();
        } else {
            LogUtils.w(TAG, "#sendMessage - mWorkerHandler is null");
        }
    }

    public void sendMessageDelayed(Message msg, long delayTime) {
        if (mWorkerHandler != null) {
            mWorkerHandler.sendMessageDelayed(msg, delayTime);
        } else {
            LogUtils.w(TAG, "#sendMessageDelayed - mWorkerHandler is null");
        }
    }

    private void handleTimeout() {
        LogUtils.d(TAG, "handleTimeout - should stop service");
        if (mWorkerThreadListener != null) {
            mWorkerThreadListener.onTimeout();
        }
    }
}
