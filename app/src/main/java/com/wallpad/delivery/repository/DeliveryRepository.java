package com.wallpad.delivery.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;


import com.wallpad.delivery.data.local.room.AppDatabase;
import com.wallpad.delivery.data.local.room.QueriesDao;
import com.wallpad.delivery.data.model.Delivery;

import java.util.ArrayList;
import java.util.List;


public class DeliveryRepository {
    private final QueriesDao mQueriesDao;

    public DeliveryRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        mQueriesDao = appDatabase.queries();
    }

    public LiveData<List<Delivery>> getNotifyData() {
        return mQueriesDao.getAllNotifications();
    }

    public void writeToDatabase(@NonNull List<Delivery> Deliverys) {
        for (Delivery item : Deliverys) {
            item.setId("id" + Deliverys.indexOf(item));
            mQueriesDao.addNewNotification(item);
        }
    }

    public List<Delivery> initDummyNotifyData() {
        List<Delivery> listNoti = new ArrayList<>();
        // add dummy data
        for (int i = 0; i < 3; i++) {
            Delivery delivery = new Delivery();
            delivery.setId("" + i);
            delivery.setName("Lorem Ipsum");
            delivery.setPayment("Ipsum");
            delivery.setTimeSend("2019.07.25 오후 07:32");
            delivery.setTimeReceive("2019.07.26 오후 07:32");
            listNoti.add(delivery);
        }
        return listNoti;
    }
}
