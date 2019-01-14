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
                    AddGeoAreaType ui = new AddGeoAreaType(geoAreaTypeList);
                    ui.run();
                    break;
                case 2:
                    GetListGeoAreaTypes ui2 = new GetListGeoAreaTypes(geoAreaTypeList);
                    ui2.run();
                    break;
                case 3:
                    AddGeoArea ui3 = new AddGeoArea(geoAreaList, geoAreaTypeList);
                    ui3.run();
                    break;
                case 4:
                    GetListOfTypeOfGeoArea ui4 = new GetListOfTypeOfGeoArea(geoAreaList, geoAreaTypeList);
                    ui4.run();
                    break;
                case 5:
                    DefineSensorType ui5 = new DefineSensorType(sensorTypeList);
                    ui5.run();
                    break;
                case 6:
                    AddSensorToGeoArea ui6 = new AddSensorToGeoArea(geoAreaList, sensorTypeList);
                    ui6.run();
                    break;
                case 7:
                    AddGeoAreaToAnotherGeoArea ui7 = new AddGeoAreaToAnotherGeoArea(geoAreaList);
                    ui7.run();
                    break;
                case 8:
                    InsertedGeoArea ui8 = new InsertedGeoArea(geoAreaList);
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
                    ConfHouseLocation ui101 = new ConfHouseLocation(house);
                    ui101.run();
                    break;
                case 2:
                    AddRoom ui105 = new AddRoom(house);
                    ui105.run();
                    break;
                case 3:
                    runHouseGridUI();
                    break;
                case 4:
                    AddSensorToRoom ui253 = new AddSensorToRoom(house, roomList, sensorTypeList);
                    ui253.run();
                    break;
                case 5:
                    GetListOfSensorsRoom ui250 = new GetListOfSensorsRoom(house);
                    ui250.run();
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
                    CreateHouseGrid ui130 = new CreateHouseGrid(gridList);
                    ui130.run();
                    break;
                case 2:
                    AddPowerSourceToHouseGrid ui135 = new AddPowerSourceToHouseGrid(gridList, powerSourceTypeList);
                    ui135.run();
                    break;
                case 3:
                    AttachRoomToHouseGrid ui147 = new AttachRoomToHouseGrid(gridList, roomList);
                    ui147.run();
                    break;
                case 4:
                    DetachRoomFromHouseGrid ui149 = new DetachRoomFromHouseGrid(gridList, roomList);
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
