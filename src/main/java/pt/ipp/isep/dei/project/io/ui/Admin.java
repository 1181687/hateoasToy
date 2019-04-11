package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

import java.io.FileNotFoundException;

public class Admin {

    private GeographicalAreaTypeList geographicalAreaTypeList;
    private GeographicalAreaService geographicalAreaService;
    private SensorTypeList sensorTypeList;
    private House house;
    private PowerSourceTypeList powerSourceTypeList;
    private RoomList roomList;
    private HouseService houseService;


    public Admin(GeographicalAreaTypeList geographicalAreaTypeList, GeographicalAreaService geographicalAreaService, SensorTypeList sensorTypeList, House house, PowerSourceTypeList powerSourceTypeList, RoomList roomList, HouseService houseService) {
        this.geographicalAreaTypeList = geographicalAreaTypeList;
        this.geographicalAreaService = geographicalAreaService;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.powerSourceTypeList = powerSourceTypeList;
        this.roomList = roomList;
        this.houseService = houseService;
    }


    public void runAdminGeographicalArea() throws FileNotFoundException {
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
                    AddGeoArea ui3 = new AddGeoArea();
                    ui3.run();
                    break;
                case 4:
                    GetListOfTypeOfGeoArea ui4 = new GetListOfTypeOfGeoArea(geographicalAreaService, geographicalAreaTypeList);
                    ui4.run();
                    break;
                case 5:
                    DefineSensorType ui5 = new DefineSensorType(sensorTypeList);
                    ui5.run();
                    break;
                case 6:
                    AddSensorToGeoArea ui6 = new AddSensorToGeoArea(geographicalAreaService, sensorTypeList);
                    ui6.run();
                    break;
                case 7:
                    AddGeoAreaToAnotherGeoArea ui7 = new AddGeoAreaToAnotherGeoArea(geographicalAreaService);
                    ui7.run();
                    break;
                case 8:
                    InsertedGeoArea ui8 = new InsertedGeoArea(geographicalAreaService);
                    ui8.run();
                    break;
                case 9:
                    ImportGeoAreasFromJSONOrXML ui9 = new ImportGeoAreasFromJSONOrXML(geographicalAreaService);
                    ui9.jsonGeoAreaSensors();
                    break;
                case 10:
                    ImportReadings ui10 = new ImportReadings(geographicalAreaService);
                    ui10.run();
                    break;
                case 11:
                    DeactivateSensorFromGeoArea ui11 = new DeactivateSensorFromGeoArea(geographicalAreaService);
                    ui11.run();
                    break;
                case 12:
                    RemoveSensorFromGeoArea ui12 = new RemoveSensorFromGeoArea(geographicalAreaService);
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
                    ConfHouseLocation ui101 = new ConfHouseLocation(geographicalAreaService, house);
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
                    //ui250.listOfSensors();
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
                case 13:
                    ConfigureHouseFromAFile ui13 = new ConfigureHouseFromAFile(house, houseService);
                    try {
                        ui13.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    public void runAdminOption() throws FileNotFoundException {
        int option = Menu.adminMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            if (option == 1) {
                runAdminGeographicalArea();
            }
            if (option == 2) {
                runAdminHouse();
            }
            option = Menu.adminMenu();
        }


    }
}
