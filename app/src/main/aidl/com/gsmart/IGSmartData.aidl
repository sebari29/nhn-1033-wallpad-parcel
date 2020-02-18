// IGSmartData.aidl
package com.gsmart;

import com.gsmart.IGSmartDataCallback;

interface IGSmartData {

    oneway void refreshNotificationInfo();

    oneway void refreshParkingInfo();

    oneway void refreshEnergyManagerInfo();

    oneway void refreshEnergyInfo();

    oneway void refreshDeliveryInfo(int indexPage);

    oneway void refreshVisitorInfo();

    oneway void refreshSettingsInfo();

    oneway void sendEnrollmentParking(String newParkingInfo);

    void addClientListener(IGSmartDataCallback callback);

    void removeClientListener(IGSmartDataCallback callback);

}