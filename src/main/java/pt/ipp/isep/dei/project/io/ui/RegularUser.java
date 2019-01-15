package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

public class RegularUser {

    GeoAreaTypeList mGeoAreaTypeList;
    GeoAreaList mGeoAreaList;
    SensorTypeList mSensorTypeList;
    House house;
    SensorType mSensorTypeTemperature;

    public RegularUser(GeoAreaTypeList mGeoAreaTypeList, GeoAreaList mGeoAreaList, SensorTypeList mSensorTypeList, House house, SensorType mSensorTypeTemperature) {
        this.mGeoAreaTypeList = mGeoAreaTypeList;
        this.mGeoAreaList = mGeoAreaList;
        this.mSensorTypeList = mSensorTypeList;
        this.house = house;
        this.mSensorTypeTemperature = mSensorTypeTemperature;
    }

    public void runRegularUserHouseArea() {
        int option = Menu.regularUserHouseAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui600 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui600.run3();
                    break;
                case 2:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui620 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui620.run();
                    break;
                case 3:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui623 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui623.run2();
                    break;
            }
            option = Menu.regularUserHouseAreaMenu();
        }
    }

    public void runRegularUserRooms() {
        int option = Menu.regularUserRoomsMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetCurrentAndMaxTempRoom ui605 = new GetCurrentAndMaxTempRoom(house, mSensorTypeTemperature);
                    ui605.run1();
                    break;
                case 2:
                    GetCurrentAndMaxTempRoom ui610 = new GetCurrentAndMaxTempRoom(house, mSensorTypeTemperature);
                    ui610.run2();
                    break;
                case 3:
                    // JUST TESTING
                    EstimateEnergyUsedByElectricWaterHeater ui752 = new EstimateEnergyUsedByElectricWaterHeater(house);
                    ui752.run();
                    break;
            }
            option = Menu.regularUserRoomsMenu();
        }
    }

    public void runRegularUserOption() {
        int option = Menu.regularUserMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    runRegularUserHouseArea();
                    break;
                case 2:
                    runRegularUserRooms();
                    break;
            }
            option = Menu.regularUserMenu();
        }
    }
}