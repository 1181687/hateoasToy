package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        GeoAreaTypeList geoAreaTypeList = new GeoAreaTypeList();
        GeoAreaList geoAreaList = new GeoAreaList();
        RoomList roomList = new RoomList();
        DeviceList deviceList = new DeviceList();
        HouseGridList gridList = new HouseGridList();

        // MOCK OBJECTS
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        SensorTypeList sensorTypeList = new SensorTypeList();
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeoAreaType geoAreaType = new GeoAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geoAreaType, location, areaShape);
        geoAreaList.addGeoAreaInASpecificPosition(0, insertedGeoArea);

        // Sensor
        LocalDateTime date1 = LocalDate.of(2018, 12, 1).atTime(15, 00, 00);
        LocalDateTime date2 = LocalDate.of(2019, 12, 1).atTime(16, 00, 00);
        SensorType sensorTypeTemperature = new SensorType("temperature");
        Sensor sensor = new Sensor("sensor1", date1, sensorTypeTemperature, location);
        Measurement temp1 = new Measurement(20, date1);
        Measurement temp2 = new Measurement(22, date2);
        sensor.addMeasurementToList(temp1);
        sensor.addMeasurementToList(temp2);
        // Add sensor to the Inserted area
        insertedGeoArea.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(sensor);

        // House
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, gridList, address, insertedGeoArea);
        houseEdificioB.getmInsertedGeoArea().setInsertedIn(insertedGeoArea);

        // House Grids
        String houseGridName = "Main Grid";
        double maximumContractedPower = 200;
        HouseGrid houseGrid = new HouseGrid(houseGridName, maximumContractedPower, roomList);
        gridList.addHouseGridToTheList(houseGrid);

        // Rooms
        String name = "B107";
        int houseFloor = 1;
        double height = 3.5;
        double length = 11;
        double width = 7;
        Dimensions dimensions = new Dimensions(height, length, width);
        Room room1 = new Room(name, houseFloor, dimensions);
        houseEdificioB.addRoom(room1);
        room1.addSensorToTheListOfSensorsInTheRoom(sensor);
        houseEdificioB.addRoom(room1);

        String name2 = "B109";
        Room room2 = new Room(name2, houseFloor, dimensions);
        houseEdificioB.addRoom(room2);

        //GridList
        String name3 = "C205";
        int houseFloor3 = 1;
        double height3 = 3.5;
        double length3 = 11;
        double width3 = 7;
        Dimensions dimensions2 = new Dimensions(height3, length3, width3);
        Room room3 = new Room(name3, houseFloor3, dimensions2);


        double luminousFlux = 10.0;
        double nominalPower = 44;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower);
        Device dev1 = new Device("Lamp1", room1, deviceSpecs1);

        DeviceSpecs specFridge = new Fridge(100, 100, 100, 100);
        //DeviceSpecs specWashing = new WashingMachine(100, 100);
        //DeviceSpecs specDishWasher = new DishWasher(100, 100);
        Device dev100 = new Device("FridgeAriston", room1, specFridge);
        //Device dev200 = new Device("WashingMachineBosh", room1, specWashing);
        //Device dev300 = new Device("DishWasher", room1, specDishWasher);


        room1.addDevice(dev1);
        room1.addDevice(dev100);

        luminousFlux = 10.0;
        nominalPower = 44;
        deviceSpecs1 = new Lamp(luminousFlux, nominalPower);
        Device dev10 = new Device("Lamp2", room2, deviceSpecs1);
        room2.addDevice(dev10);
        //room2.addDevice(dev200);
        //room3.addDevice(dev300);

        roomList.addRoom(room1);
        roomList.addRoom(room2);
        RoomList roomList2 = new RoomList();
        roomList.addRoom(room3);

        HouseGrid houseGrid1 = new HouseGrid("HG1", 1000, roomList);
        HouseGrid houseGrid2 = new HouseGrid("HG2", 1000, roomList2);
        gridList.addHouseGridToTheList(houseGrid1);
        gridList.addHouseGridToTheList(houseGrid2);

        /*
        //mock device
        double luminousFlux = 10.0;
        double nominalPower = 44;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux, nominalPower);
        Device dev1 = new Device("Lamp1", room1, deviceSpecs1);

        room1.addDevice(dev1);
        */

        // Devices
        double hotWaterTemp = 55;
        double maximumVolume = 100;
        double nominalPower1 = 44;
        double performanceRatio = 0.9;
        DeviceSpecs deviceSpecs2 = new ElectricWaterHeater(hotWaterTemp, maximumVolume, nominalPower1, performanceRatio);
        Device dev2 = new Device("Titan SCR2 N-120", room1, deviceSpecs2);
        room1.addDevice(dev2);

        // Power Source Type (and List)
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geoAreaTypeList, geoAreaList, deviceList, sensorTypeList, houseEdificioB, powerSourceTypeList, roomList, gridList);
        RegularUser regularUser = new RegularUser(geoAreaTypeList, geoAreaList, sensorTypeList, houseEdificioB, sensorTypeTemperature);

        int userOption = -1;

        while (userOption != 0) {
            userOption = Menu.usersMenu();

            if (userOption == 1) {
                admin.runAdminOption();
            }
            if (userOption == 2) {
                regularUser.runRegularUserOption();
            }
        }
    }
}