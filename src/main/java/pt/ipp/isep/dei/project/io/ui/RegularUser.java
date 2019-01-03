package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

public class RegularUser {

    GeoAreaTypeList geoAreaTypeList;
    GeoAreaList geoAreaList;
    SensorTypeList sensorTypeList;
    House house;
    SensorType sensorTypeTemperature;

    public RegularUser(GeoAreaTypeList geoAreaTypeList, GeoAreaList geoAreaList, SensorTypeList sensorTypeList, House house, SensorType sensorTypeTemperature) {
        this.geoAreaTypeList = geoAreaTypeList;
        this.geoAreaList = geoAreaList;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.sensorTypeTemperature = sensorTypeTemperature;
    }

    public void runRegularUserHouseArea() {
        int option = Menu.regularUserHouseAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    US600UI ui600 = new US600UI(house, sensorTypeTemperature);
                    ui600.run();
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
                    US605UI ui605 = new US605UI(house, sensorTypeTemperature);
                    ui605.run1();
                    break;
                case 2:
                    US605UI ui610 = new US605UI(house, sensorTypeTemperature);
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



