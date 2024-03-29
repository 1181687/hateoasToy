package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;
import pt.ipp.isep.dei.project.services.*;

import java.io.FileNotFoundException;

public class Admin {

    private GeographicalAreaService geographicalAreaService;
    private GeoAreaTypeService geoAreaTypeService;
    private SensorTypeList sensorTypeList;
    private House house;
    private PowerSourceTypeList powerSourceTypeList;
    private RoomList roomList;
    private HouseService houseService;
    private GeoAreaSensorService geoAreaSensorService;
    private RoomSensorService roomSensorService;
    private SensorTypeService sensorTypeService;
    private RoomService roomService;
    private HouseGridService houseGridService;


    public Admin(GeographicalAreaService geographicalAreaService,
                 GeoAreaTypeService geoAreaTypeService, SensorTypeList sensorTypeList, PowerSourceTypeList powerSourceTypeList,
                 RoomList roomList, HouseService houseService, GeoAreaSensorService geoAreaSensorService, RoomSensorService roomSensorService,
                 SensorTypeService sensorTypeService, RoomService roomService, House house, HouseGridService houseGridService) {
        this.geographicalAreaService = geographicalAreaService;
        this.geoAreaTypeService = geoAreaTypeService;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.powerSourceTypeList = powerSourceTypeList;
        this.roomList = roomList;
        this.houseService = houseService;
        this.geoAreaSensorService = geoAreaSensorService;
        this.roomSensorService = roomSensorService;
        this.sensorTypeService = sensorTypeService;
        this.roomService = roomService;
        this.houseGridService = houseGridService;
    }


    public void runAdminGeographicalArea() throws FileNotFoundException {
        int option = Menu.adminGeoAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    AddGeoAreaType ui = new AddGeoAreaType(geoAreaTypeService);
                    ui.run();
                    break;
                case 2:
                    GetListGeoAreaTypes ui2 = new GetListGeoAreaTypes(geoAreaTypeService);
                    ui2.run();
                    break;
                case 3:
                    AddNewGeographicalArea ui3 = new AddNewGeographicalArea(geographicalAreaService, geoAreaTypeService);
                    ui3.run();
                    break;
                case 4:
                    GetListOfTypeOfGeoArea ui4 = new GetListOfTypeOfGeoArea(geographicalAreaService, geoAreaTypeService);
                    ui4.run();
                    break;
                case 5:
                    DefineSensorType ui5 = new DefineSensorType(sensorTypeService);
                    ui5.run();
                    break;
                case 6:
                    AddSensorToGeoArea ui6 = new AddSensorToGeoArea(geographicalAreaService, sensorTypeService, geoAreaSensorService);
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
                    ImportGeoAreasAndSensors ui9 = new ImportGeoAreasAndSensors(geographicalAreaService, geoAreaSensorService);
                    ui9.run();
                    break;
                case 10:
                    ImportReadings ui10 = new ImportReadings(geoAreaSensorService, roomSensorService);
                    ui10.run(1);
                    break;
                case 11:
                    DeactivateSensorFromGeoArea ui11 = new DeactivateSensorFromGeoArea(geographicalAreaService, geoAreaSensorService);
                    ui11.run();
                    break;
                case 12:
                    RemoveSensorFromGeoArea ui12 = new RemoveSensorFromGeoArea(geographicalAreaService, geoAreaSensorService);
                    ui12.run();
                    break;
            }
            option = Menu.adminGeoAreaMenu();
        }
    }

    public void runAdminHouse() throws FileNotFoundException {
        int option = Menu.adminHouseMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    ConfigureHouseLocation ui101 = new ConfigureHouseLocation(house, houseService, geographicalAreaService);
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
                    AddSensorToRoom ui253 = new AddSensorToRoom(sensorTypeService, roomService, roomSensorService);
                    ui253.run();
                    break;
                case 5:
                    GetListOfSensorsAndDevicesRoom ui201 = new GetListOfSensorsAndDevicesRoom(this.roomService, this.roomSensorService);
                    ui201.listOfDevices();
                    break;
                case 6:
                    GetListOfSensorsAndDevicesRoom ui250 = new GetListOfSensorsAndDevicesRoom(this.roomService, this.roomSensorService);
                    ui250.listOfSensors();
                    break;
                case 7:
                    EditConfigurationDevice ui215 = new EditConfigurationDevice(house);
                    ui215.run();
                    break;
                case 8:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(houseService);
                    ui230.run();
                    break;
                case 9:
                    GetNominalPowerOfGrid ui172 = new GetNominalPowerOfGrid(houseService);
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
                    ConfigureHouseFromAFile ui13 = new ConfigureHouseFromAFile(houseService);
                    try {
                        ui13.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 14:
                    ImportRoomSensors ui260 = new ImportRoomSensors(roomService, roomSensorService);
                    ui260.run();
                    break;
                case 15:
                    ImportReadings ui15 = new ImportReadings(geoAreaSensorService, roomSensorService);
                    ui15.run(2);
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
                    CreateHouseGrid ui130 = new CreateHouseGrid(houseGridService);
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
