package com.wallpad.delivery.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.wallpad.delivery.data.local.room.AppDatabase;
import com.wallpad.delivery.data.local.room.QueriesDao;
import com.wallpad.delivery.data.model.Delivery;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link DeliveryRepository}
 */
public class DeliveryRepository {
    private final QueriesDao mQueriesDao;

    /**
     * Constructor
     *
     * @param application
     */
    public DeliveryRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        mQueriesDao = appDatabase.queries();
    }

    /**
     * Get delivery data from database
     *
     * @return delivery info list
     */
    public LiveData<List<Delivery>> getDeliveryData() {
        return mQueriesDao.getAllNotifications();
    }

    /**
     * Update database
     *
     * @param deliveryInfoList
     */
    public void writeToDatabase(@NonNull List<Delivery> deliveryInfoList) {
        for (Delivery item : deliveryInfoList) {
            item.setId("id" + deliveryInfoList.indexOf(item));
            mQueriesDao.addNewNotification(item);
        }
    }

    /**
     * Init dummy data
     *
     * @return dummy data
     */
    public List<Delivery> initDummyNotifyData() {
        List<Delivery> listNoti = new ArrayList<>();
        // add dummy data
        for (int i = 0; i < 128; i++) {
            Delivery delivery = new Delivery();
            delivery.setId("" + i);
            delivery.setName("Lorem Ipsum");
            delivery.setPayment("Ipsum " + i);
            delivery.setTimeSend("2019.07.25 오후 07:32");
            delivery.setTimeReceive("2019.07.26 오후 07:32");
            listNoti.add(delivery);
        }
        return listNoti;
    }
}
