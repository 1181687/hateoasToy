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
                    US620UI ui600 = new US620UI(house);
                    ui600.run3();
                    break;
                case 2:
                    US620UI ui620 = new US620UI(house);
                    ui620.run();
                    break;
                case 3:
                    US620UI ui623 = new US620UI(house);
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
                    US605UI ui605 = new US605UI(house, mSensorTypeTemperature);
                    ui605.run1();
                    break;
                case 2:
                    US605UI ui610 = new US605UI(house, mSensorTypeTemperature);
                    ui610.run2();
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