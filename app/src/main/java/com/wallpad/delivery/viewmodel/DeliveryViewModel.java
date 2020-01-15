package com.wallpad.delivery.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wallpad.basemvvm.viewmodel.BaseAndroidViewModel;
import com.wallpad.delivery.common.DateTimeUtils;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.repository.DeliveryRepository;

import java.util.List;

/**
 * {@link DeliveryViewModel}
 */
public class DeliveryViewModel extends BaseAndroidViewModel {

    private final DeliveryRepository mRepository;

    public final MutableLiveData<String> txtAMorPM = new MutableLiveData<>();

    /**
     * Constructor
     *
     * @param application {@link Application}
     */
    public DeliveryViewModel(Application application) {
        super(application);
        mRepository = new DeliveryRepository(application);
    }

    private LiveData<List<Delivery>> getNotifyDataFromDatabase() {
        return mRepository.getDeliveryData();
    }

    /**
     * Update time info to AM or PM
     */
    public void changeTxtAMorPM() {
        txtAMorPM.postValue(new DateTimeUtils().getAMorPMTime());
    }

    /**
     * Get all delivery info
     *
     * @return delivery info list
     */
    public MutableLiveData<List<Delivery>> getDeliveryData() {
        LiveData<List<Delivery>> queriedData = getNotifyDataFromDatabase();
        final MutableLiveData<List<Delivery>> returnData = new MutableLiveData<>();
        queriedData.observeForever(new Observer<List<Delivery>>() {
            @Override
            public void onChanged(List<Delivery> Notices) {
                if (Notices.isEmpty()) {
                    List<Delivery> dummyList = mRepository.initDummyNotifyData();
                    returnData.postValue(dummyList);
                    mRepository.writeToDatabase(dummyList);
                } else {
                    returnData.postValue(Notices);
                }
                queriedData.removeObserver(this);
            }
        });
        return returnData;
    }
}
