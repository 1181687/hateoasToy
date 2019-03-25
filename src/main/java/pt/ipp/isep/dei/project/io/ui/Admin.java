package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

public class Admin {
    private GeographicalAreaTypeList geographicalAreaTypeList;
    private GeographicalAreaList geographicalAreaList;
    private SensorTypeList sensorTypeList;
    private House house;
    private PowerSourceTypeList powerSourceTypeList;
    private RoomList roomList;

    public Admin(GeographicalAreaTypeList geographicalAreaTypeList, GeographicalAreaList geographicalAreaList, SensorTypeList sensorTypeList, House house, PowerSourceTypeList powerSourceTypeList, RoomList roomList) {
        this.geographicalAreaTypeList = geographicalAreaTypeList;
        this.geographicalAreaList = geographicalAreaList;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.powerSourceTypeList = powerSourceTypeList;
        this.roomList = roomList;
    }


    public void runAdminGeographicalArea() {
        int option = Menu.adminGeoAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    AddGeoAreaType ui = new AddGeoAreaType(geographicalAreaTypeList);
                    ui.run();
                    break;
                case 2:
                    GetListGeoAreaTypes ui2 = new GetListGeoAreaTypes(geographicalAreaTypeList);
                    ui2.run();
                    break;
                case 3:
                    AddGeoArea ui3 = new AddGeoArea(geographicalAreaList, geographicalAreaTypeList);
                    ui3.run();
                    break;
                case 4:
                    GetListOfTypeOfGeoArea ui4 = new GetListOfTypeOfGeoArea(geographicalAreaList, geographicalAreaTypeList);
                    ui4.run();
                    break;
                case 5:
                    DefineSensorType ui5 = new DefineSensorType(sensorTypeList);
                    ui5.run();
                    break;
                case 6:
                    AddSensorToGeoArea ui6 = new AddSensorToGeoArea(geographicalAreaList, sensorTypeList);
                    ui6.run();
                    break;
                case 7:
                    AddGeoAreaToAnotherGeoArea ui7 = new AddGeoAreaToAnotherGeoArea(geographicalAreaList);
                    ui7.run();
                    break;
                case 8:
                    InsertedGeoArea ui8 = new InsertedGeoArea(geographicalAreaList);
                    ui8.run();
                    break;
                case 9:
                    ImportGeoAreasFromJSONAndXML ui9 = new ImportGeoAreasFromJSONAndXML(geographicalAreaList);
                    ui9.run();
                    break;
                case 10:
                    ImportReadingsFromCSV ui10 = new ImportReadingsFromCSV(geographicalAreaList);
                    ui10.run();
                    break;
                case 11:
                    DeactivateSensorFromGeoArea ui11 = new DeactivateSensorFromGeoArea(geographicalAreaList);
                    ui11.run();
                    break;
                case 12:
                    RemoveSensorFromGeoArea ui12 = new RemoveSensorFromGeoArea(geographicalAreaList);
                    ui12.run();
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
                    ConfHouseLocation ui101 = new ConfHouseLocation(geographicalAreaList, house);
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
                    GetListOfSensorsAndDevicesRoom ui201 = new GetListOfSensorsAndDevicesRoom(house);
                    ui201.listOfDevices();
                    break;
                case 6:
                    GetListOfSensorsAndDevicesRoom ui250 = new GetListOfSensorsAndDevicesRoom(house);
                    ui250.listOfSensors();
                    break;
                case 7:
                    EditConfigurationDevice ui215 = new EditConfigurationDevice(house);
                    ui215.run();
                    break;
                case 8:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(house);
                    ui230.run();
                    break;
                case 9:
                    GetNominalPowerOfGrid ui172 = new GetNominalPowerOfGrid(house);
                    ui172.run();
                    break;
                case 10:
                    AddDeviceToRoom ui210 = new AddDeviceToRoom(house);
                    ui210.run();
                    break;
                case 11:
                    DeleteDeviceFromRoom ui220 = new DeleteDeviceFromRoom(house);
                    ui220.run();
                    break;
                case 12:
                    GetEnergyConsumptionDevice ui720 = new GetEnergyConsumptionDevice(house);
                    ui720.run();
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
                    CreateHouseGrid ui130 = new CreateHouseGrid(house);
                    ui130.run();
                    break;
                case 2:
                    AddPowerSourceToHouseGrid ui135 = new AddPowerSourceToHouseGrid(house, powerSourceTypeList);
                    ui135.run();
                    break;
                case 3:
                    AttachRoomToHouseGrid ui147 = new AttachRoomToHouseGrid(house, roomList);
                    ui147.run();
                    break;
                case 4:
                    DetachRoomFromHouseGrid ui149 = new DetachRoomFromHouseGrid(house, roomList);
                    ui149.run();
                    break;
                case 5:
                    GetDevicesInHouseGrid ui160 = new GetDevicesInHouseGrid(house);
                    ui160.run();
                    break;
                case 6:
                    GetEnergyConsumptionOfAGrid ui722 = new GetEnergyConsumptionOfAGrid(house);
                    ui722.run();
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
