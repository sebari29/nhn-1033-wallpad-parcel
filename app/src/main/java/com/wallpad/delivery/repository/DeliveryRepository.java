package com.wallpad.delivery.repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.common.APIContentProviderHelper;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.parcelInfoInquiry.response.Data;
import com.wallpad.net.sample.parcelInfoInquiry.response.HNMLInquiryResponse;
import com.wallpad.net.sample.parcelInfoInquiry.response.Output;
import com.wallpad.net.sample.parcelnotification.request.HNMLParcelNotification;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.wallpad.delivery.common.Constant.DATETIME_YYYYMMDDAHHMM;
import static com.wallpad.delivery.common.Constant.DATETIME_YYYYMMDDTHHMMSS;

/**
 * {@link DeliveryRepository}
 */
public class DeliveryRepository {
    String currentData = "";

    /**
     * Constructor
     *
     * @param application
     */
    public DeliveryRepository(Application application) {
    }

    /**
     * Get delivery data from database
     *
     * @return delivery info list
     */
    public List<Delivery> getDeliveryData(APIContentProviderHelper apiContentProviderHelper) {
        String response = apiContentProviderHelper.getNotificationResponse();
        if (response.equals(currentData)) {
            return null;
        } else {
            currentData = response;
        }
        LogUtils.d("getDeliveryData ", "handleLoadNotification() - " + response);
        if (response != null && !response.isEmpty()) {
            return  getListDeliveriesFrom(response);
        }
        return null;
    }
    public Delivery getDeliveryFrom(String data){
        try {
            HNMLParcelNotification request = new XMLUtils<HNMLParcelNotification>().convertXMLToObject(data, HNMLParcelNotification.class);
            HashMap<String, Object> map = request.controlRequest.getListInput().getInput().getMap();
            Delivery delivery = new Delivery();
            delivery.setId((String) map.get("id"));
            delivery.setName((String) map.get("BoxNo"));
            if (((String) map.get("EventType")).equals("Arrive")) {
                //arrived
                delivery.setStatus("0");
                SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_YYYYMMDDTHHMMSS);
                Date date = sdf.parse((String) map.get("Time"));
                String dateStr = new SimpleDateFormat(DATETIME_YYYYMMDDAHHMM, Locale.getDefault()).format(date);
                delivery.setTimeSend(dateStr);
                return delivery;
//                    adapter.notifyDataSetChanged();
            } else {
                // received
                // refreshData();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Delivery> getListDeliveriesFrom(String dataString){
        HNMLInquiryResponse hnmlParcelInfoInquiry = null;
        try {
            hnmlParcelInfoInquiry = new XMLUtils<HNMLInquiryResponse>().convertXMLToObject(dataString, HNMLInquiryResponse.class);
            LogUtils.d(hnmlParcelInfoInquiry.getControlResponse().getOutputList().Output.size());
            List<Delivery> deliveries = new ArrayList<>();
            if (!hnmlParcelInfoInquiry.getControlResponse().getOutputList()._size.equals("0")) {
                for (Output output : hnmlParcelInfoInquiry.getControlResponse().getOutputList().Output) {
                    Delivery delivery = new Delivery();
                    for (Data data : output.getData()) {
                        if (data.getName().equals("ArriveTime") && data.getValue() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_YYYYMMDDTHHMMSS);
                            Date date = sdf.parse(data.getValue());
                            String dateStr = new SimpleDateFormat(DATETIME_YYYYMMDDAHHMM, Locale.getDefault()).format(date);
                            delivery.setTimeSend(dateStr);
                        } else if (data.getName().equals("ReceiveTime") && data.getValue() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_YYYYMMDDTHHMMSS);
                            Date date = sdf.parse(data.getValue());
                            String dateStr = new SimpleDateFormat(DATETIME_YYYYMMDDAHHMM, Locale.getDefault()).format(date);
                            delivery.setTimeReceive(dateStr);
                        } else if (data.getName().equals("EventType")) {
                            delivery.setStatus(data.getValue());
                        } else if (data.getName().equals("Name")) {
//                            delivery.setName(data.getValue());
                        } else if (data.getName().equals("id")) {
                            delivery.setId(data.getValue());
                        } else if (data.getName().equals("BoxNo")) {
                            delivery.setName(data.getValue());
                        } else if (data.getName().equals("totalcount")) {
                        }
                    }
                    deliveries.add(delivery);
                }
                return (deliveries);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Init dummy data
     *
     * @return dummy data
     */
    public List<Delivery> initDummyNotifyData() {
        List<Delivery> listNoti = new ArrayList<>();
        // add dummy data
        for (int i = 0; i < 10; i++) {
            Delivery delivery = new Delivery();
            delivery.setId("" + i);
            delivery.setName("Lorem Ipsum" + i);
            delivery.setStatus("Ipsum " + i);
            delivery.setTimeSend("2019.07.25 오후 07:32");
            delivery.setTimeReceive("2019.07.26 오후 07:32");
            listNoti.add(delivery);
        }
        return listNoti;
    }
}
