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

    oneway void refreshParkingParkingInquiry();

    oneway void refreshParkingLot(int indexPage);

    oneway void refreshSubmenuEnergyElectricityInfo(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyGasInfo(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyWaterInfo(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyHotWaterInfo(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyHeatingInfo(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyCoolingInfo(boolean isRemoteApp);



    oneway void refreshSubmenuEnergyElectricityInfoLastMonth(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyGasInfoLastMonth(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyWaterInfoLastMonth(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyHotWaterInfoLastMonth(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyHeatingInfoLastMonth(boolean isRemoteApp);

    oneway void refreshSubmenuEnergyCoolingInfoLastMonth(boolean isRemoteApp);



    oneway void refreshEnergyElectricityAmount(int amount);
    oneway void refreshEnergyGasAmount(int amount);
    oneway void refreshEnergyWaterAmount(int amount);
    oneway void refreshEnergyHotWaterAmount(int amount);
    oneway void refreshEnergyHeatingAmount(int amount);
    oneway void refreshEnergyCoolingAmount(int amount);


    oneway void sendEnrollmentParking(String newParkingInfo);

    void addClientListener(IGSmartDataCallback callback);

    void removeClientListener(IGSmartDataCallback callback);

}