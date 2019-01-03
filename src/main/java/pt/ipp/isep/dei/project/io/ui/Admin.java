package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

public class Admin {
    GeoAreaTypeList geoAreaTypeList;
    GeoAreaList geoAreaList;
    SensorTypeList sensorTypeList;
    House house;
    PowerSourceTypeList powerSourceTypeList;
    RoomList roomList;
    HouseGridList gridList;

    public Admin(GeoAreaTypeList geoAreaTypeList, GeoAreaList geoAreaList, SensorTypeList sensorTypeList, House house, PowerSourceTypeList powerSourceTypeList, RoomList roomList, HouseGridList gridList) {
        this.geoAreaTypeList = geoAreaTypeList;
        this.geoAreaList = geoAreaList;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.powerSourceTypeList = powerSourceTypeList;
        this.roomList = roomList;
        this.gridList = gridList;
    }


    public void runAdminGeographicalArea() {
        int option = Menu.adminGeoAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    US1UI ui = new US1UI(geoAreaTypeList);
                    ui.run();
                    break;
                case 2:
                    US2UI ui2 = new US2UI(geoAreaTypeList);
                    ui2.run();
                    break;
                case 3:
                    US3UI ui3 = new US3UI(geoAreaList, geoAreaTypeList);
                    ui3.run();
                    break;
                case 4:
                    US4UI ui4 = new US4UI(geoAreaList, geoAreaTypeList);
                    ui4.run();
                    break;

                case 5:
                    US5UI ui5 = new US5UI(sensorTypeList);
                    ui5.run();
                    break;
                case 6:
                    US6UI ui6 = new US6UI(geoAreaList, sensorTypeList);
                    ui6.run();
                    break;
                case 7:
                    US7UI ui7 = new US7UI(geoAreaList);
                    ui7.run();
                    break;
                case 8:
                    US8UI ui8 = new US8UI(geoAreaList);
                    ui8.run();
                    break;
            }
            option = Menu.adminGeoAreaMenu();
        }
    }

    public void runAdminHouse() {
        int option = Menu.adminHouseMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    US101UI ui101 = new US101UI(house);
                    ui101.run();
                    break;
                case 2:
                    US105UI ui105 = new US105UI(house);
                    ui105.run();
                    break;
                case 3:
                    runHouseGridUI();
                    break;
                case 4:
                    US253UI ui253 = new US253UI(house, roomList, sensorTypeList);
                    ui253.run();
                    break;
            }
            option = Menu.adminHouseMenu();
        }
    }

    public void runHouseGridUI() {
        int option = Menu.houseGridMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    US130UI ui130 = new US130UI(gridList);
                    ui130.run();
                    break;
                case 2:
                    US135UI ui135 = new US135UI(gridList, powerSourceTypeList);
                    ui135.run();
                    break;
                case 3:
                    US147UI ui147 = new US147UI(gridList, roomList);
                    ui147.run();
                    break;
                case 4:
                    US149UI ui149 = new US149UI(gridList, roomList);
                    ui149.run();
                    break;
            }
            option = Menu.houseGridMenu();
        }
    }

    public void runAdminOption() {
        int option = Menu.adminMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    runAdminGeographicalArea();
                    break;
                case 2:
                    runAdminHouse();
                    break;
            }
            option = Menu.adminMenu();
        }


    }
}
