/*
 * Copyright 2017 Sony Corporation
 * Copyright 2018 Sony Visual Products Inc.
 */
package com.wallpad.delivery.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.event.ParcelEvent;

class WorkerHandler extends Handler {

    private static final String TAG = WorkerHandler.class.getSimpleName();
    private static final int MESSAGE_GET_DATA = 1000;

    private final Context mContext;
    private final MessageQueue mMessageQueue;
    private final MessageWaitingTimeoutListener mListener;

    interface MessageWaitingTimeoutListener {
        void onTimeout();
    }

    private final MessageQueue.IdleHandler mIdleHandler = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            LogUtils.d(TAG, "queueIdle");
            // In case there is no more message in queue, wait 1 minute before stop service
            // Send an empty message with delayed time = 1 minute.
//            sendEmptyMessageDelayed(MESSAGE_WAITING_TIMEOUT_LOGIN, MESSAGE_WAITING_TIMEOUT_LOGIN_DURATION);
            return true;
        }
    };

    WorkerHandler(Context context, Looper looper, MessageWaitingTimeoutListener listener) {
        super(looper);
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        mContext = context;
        mMessageQueue = looper.getQueue();
        mMessageQueue.addIdleHandler(mIdleHandler);
        mListener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        LogUtils.d(TAG, "WorkerHandler - mHandlerThread: is main thread ? " + Looper.getMainLooper().equals(Looper.myLooper()));
        if (msg != null) {
            LogUtils.d(TAG, "handleMessage - what = " + msg.what);
            // Always cancel waiting timeout
            switch (msg.what) {
                case MESSAGE_GET_DATA:
//                    handleGetData();
                    break;
                default:
                    handleGetData(msg);
                    break;
            }
        }
    }

    private void handleGetData(Message message) {
        ParcelEvent notificationEvent = (ParcelEvent) message.obj;
        ParcelInquiryOperation parcelInquiryOperation = new ParcelInquiryOperation(mContext, notificationEvent.getOnDataChangedListener());
        parcelInquiryOperation.handleLoadNotification();
    }

    /**
     * Handle received intent
     */
    public void sendGetDataMessage() {
        LogUtils.d(TAG, "sendGetDataMessage");
        sendEmptyMessage(MESSAGE_GET_DATA);
    }

    /**
     * Clear timeout message
     */
    public void clearGetDataMessage() {
        removeMessages(MESSAGE_GET_DATA);
    }

    /**
     * Handle timeout
     */
    private void handleTimeOut() {
        mMessageQueue.removeIdleHandler(mIdleHandler);
        LogUtils.d(TAG, "#handleTimeOut() - MESSAGE_WAITING_TIMEOUT_LOGIN ==> destroy thread");
        if (mListener != null) {
            mListener.onTimeout();
        }
    }
}
