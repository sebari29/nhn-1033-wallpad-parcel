package com.wallpad.delivery.repository;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.MainActivity;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.common.DateTimeUtils;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.parcelInfoInquiry.request.HNMLParcelInfoInquiry;
import com.wallpad.net.sample.parcelInfoInquiry.response.Data;
import com.wallpad.net.sample.parcelInfoInquiry.response.HNMLInquiryResponse;
import com.wallpad.net.sample.parcelInfoInquiry.response.Output;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class ParcelInquiryOperation {

    private final static String TAG = ParcelInquiryOperation.class.getSimpleName();

    private Context mContext;

    private MainActivity.OnDataChangedListener mCallback;

    ParcelInquiryOperation(Context context, MainActivity.OnDataChangedListener callback) {
        if (context == null) {
            throw new IllegalArgumentException("#handleOnOffDemoMode() - Context must not be null");
        }
        mContext = context;
        mCallback = callback;
    }

    public void handleLoadNotification() {
        APIContentProviderHelper apiContentProviderHelper = new APIContentProviderHelper(mContext.getContentResolver());
        String response = apiContentProviderHelper.getNotificationResponse();
        LogUtils.d(TAG, "handleLoadNotification() - " + response);
        if (response != null && !response.isEmpty()) {
            HNMLInquiryResponse hnmlParcelInfoInquiry = null;
            try {
                hnmlParcelInfoInquiry = new XMLUtils<HNMLInquiryResponse>().convertXMLToObject(response, HNMLInquiryResponse.class);
                LogUtils.d(hnmlParcelInfoInquiry.getControlResponse().getOutputList().Output.size());
                List<Delivery> deliveries = new ArrayList<>();
                for (Output output : hnmlParcelInfoInquiry.getControlResponse().getOutputList().Output) {
                    Delivery delivery = new Delivery();
                    for (Data data : output.getData()) {
                        if (data.getName().equals("ArriveTime")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                             Date date =    sdf.parse(data.getValue());
                            String dateStr  =  new SimpleDateFormat("yyyy.MM.dd a hh:mm",Locale.getDefault()).format(date);
                            delivery.setTimeSend(dateStr);
                        } else if (data.getName().equals("ReceiveTime")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date date =    sdf.parse(data.getValue());
                            String dateStr  =  new SimpleDateFormat("yyyy.MM.dd a hh:mm",Locale.getDefault()).format(date);
                            delivery.setTimeReceive(dateStr);
                        } else if (data.getName().equals("EventType")) {
                            delivery.setPayment(data.getValue());
                        } else if (data.getName().equals("Name")) {
                            delivery.setName(data.getValue());
                        } else if (data.getName().equals("id")) {
                            delivery.setId(data.getValue());
                        } else if (data.getName().equals("BoxNo")) {
                        } else if (data.getName().equals("totalcount")) {
                        }
                    }
                    deliveries.add(delivery);
                }
                mCallback.onNotificationChanged(deliveries);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // TODO: parse response and notify to UI
//        mCallback.onNotificationChanged();
    }
}