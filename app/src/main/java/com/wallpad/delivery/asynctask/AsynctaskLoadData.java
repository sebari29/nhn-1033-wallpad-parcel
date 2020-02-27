package com.wallpad.delivery.asynctask;

import android.app.Application;
import android.os.AsyncTask;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.repository.DeliveryRepository;


import java.util.List;

public class AsynctaskLoadData extends AsyncTask<APIContentProviderHelper, Void, List<Delivery>> {
    private DeliveryRepository.ICallBack iCallBack;
    private String currentData;
    private Application mApplication;

    public AsynctaskLoadData(DeliveryRepository.ICallBack iCallBack, String currentData,
                             Application mApplication){
        this.iCallBack = iCallBack;
    }
    @Override
    protected void onPreExecute(
    ) {
        super.onPreExecute();
        iCallBack.showLoading();
    }

    @Override
    protected List<Delivery> doInBackground(APIContentProviderHelper... apiContentProviderHelpers) {
        String response = apiContentProviderHelpers[0].getNotificationResponse();
        if (response.equals(currentData)) {
            return null;
        } else {
            currentData = response;
        }
        LogUtils.d(Constant.TAG, "handleLoadNotification() - " + response);
        if (response != null && !response.isEmpty()) {
            return new DeliveryRepository(mApplication).getListDeliveriesFrom(response);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Delivery> parkingInfos) {
        super.onPostExecute(parkingInfos);
        iCallBack.hideLoading();
        iCallBack.onCallBack(parkingInfos);
    }
}
