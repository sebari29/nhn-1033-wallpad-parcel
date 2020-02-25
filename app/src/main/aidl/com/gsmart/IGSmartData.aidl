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

    oneway void refeshWeatherInfo();

    oneway void refreshSubmenuEnergyElectricityInfo();

    oneway void refreshSubmenuEnergyGasInfo();

    oneway void refreshSubmenuEnergyWaterInfo();

    oneway void refreshSubmenuEnergyHotWaterInfo();

    oneway void refreshSubmenuEnergyHeatingInfo();

    oneway void refreshSubmenuEnergyCoolingInfo();


    oneway void refreshParkingParkingInquiry();

    oneway void refreshParkingLot(int indexPage);

    oneway void sendEnrollmentParking(String newParkingInfo);

    void addClientListener(IGSmartDataCallback callback);

    void removeClientListener(IGSmartDataCallback callback);

}