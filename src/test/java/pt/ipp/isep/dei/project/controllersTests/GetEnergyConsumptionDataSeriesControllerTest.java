package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDataSeriesController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.fridge.FridgeType;
import pt.ipp.isep.dei.project.model.devices.washingmachine.WashingMachineType;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class GetEnergyConsumptionDataSeriesControllerTest {
    private GetEnergyConsumptionDataSeriesController ctrl;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

        this.ctrl = new GetEnergyConsumptionDataSeriesController(house);
    }

    @Test
    public void testGetHouseGridListToString() {
        // Arrange
        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);

        String expectedResult = "1 - Name: Grid\n";

        // Act
        String result = ctrl.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDisplayRoomListTest() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        String expectResult = "1- Name: Kitchen, House Floor: 0, Dimension - Height: 2.0, Length: 2.0, Width: 2.0\n" +
                "2- Name: Living Room, House Floor: 1, Dimension - Height: 2.0, Length: 1.5, Width: 1.3\n";

        //act
        String result = ctrl.getRoomListToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", "room", 1, dim);
        Room room1 = new Room("Laundry", "room", 2, dim);

        // FridgeSpecs Instantiation

        // ElectricWaterHeaterSpecs Instantiation

        // Device Instantiation
        FridgeType fridgeType = new FridgeType();
        WashingMachineType washingMachineType = new WashingMachineType();

        house.addRoom(room0);
        house.addRoom(room1);

        house.createDevice("Fridge", "Fridgeratah V14", room0);
        house.createDevice("WashingMachine", "Bosch Tronic 3000", room1);

        String expectedResult =
                "1 - Device: Fridgeratah V14, located in room: Kitchen\n" +
                        "2 - Device: Bosch Tronic 3000, located in room: Laundry\n";

        // Act
        String result = ctrl.getDeviceListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        // Arrange
        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);

        int expectedResult = 1;

        // Act
        int result = ctrl.getHouseGridListSize();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        House emptyHouse = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        int expectedResult = 0;

        GetEnergyConsumptionDataSeriesController ctrl = new GetEnergyConsumptionDataSeriesController(emptyHouse);

        // Act
        int result = ctrl.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoomListSize() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        int expectResult = 2;

        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getRoomListSizeEmptyList() {
        //arrange
        int expectResult = 0;

        //act
        int result = ctrl.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListSize() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        //initiate devices
        house.addRoom(room1);

        house.createDevice("Fridge", "Fridge1", room1);
        house.createDevice("Lamp", "Lamp1", room1);

        int expectResult = 2;
        //act
        int result = ctrl.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListSizeEmptyList() {
        //arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("RoomOne", "room", 2, dim);

        house.addRoom(room);

        int expectResult = 0;
        //act
        int result = ctrl.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDeviceDataSeriesToString() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("RoomOne", "room", 2, dim);

        house.addRoom(room);

        // Device Instantiation
        Device device = house.createDevice("Fridge", "Fridgeratah V14", room);

        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Reading reading3 = new Reading(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Reading reading5 = new Reading(15, time5);

        // List<Reading Configuration
        device.addReadingsToTheList(reading0);
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);
        device.addReadingsToTheList(reading3);
        device.addReadingsToTheList(reading4);
        device.addReadingsToTheList(reading5);

        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        mapToTest.put(time0, 3.0);
        mapToTest.put(time1, 5.0);
        mapToTest.put(time2, 7.0);
        mapToTest.put(time3, 10.0);
        mapToTest.put(time4, 5.0);

        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 3.0 kWh\n" +
                "Date/hour: 2019-01-24 08:00, Energy Consumption: 5.0 kWh\n" +
                "Date/hour: 2019-01-24 16:00, Energy Consumption: 7.0 kWh\n" +
                "Date/hour: 2019-01-25 10:00, Energy Consumption: 10.0 kWh\n" +
                "Date/hour: 2019-01-25 12:00, Energy Consumption: 5.0 kWh\n";

        ctrl.getRoomByPosition(0);
        ctrl.getDeviceByPosition(0);

        LocalDateTime timeToTest0 = LocalDateTime.of(2019, 01, 23, 10, 00);
        LocalDateTime timeToTest1 = LocalDateTime.of(2019, 01, 25, 13, 00);

        //Act
        String result = ctrl.getDeviceDataSeriesToString(timeToTest0, timeToTest1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDeviceDataSeriesToStringNoValidNumbers() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("RoomOne", "room", 2, dim);

        house.addRoom(room);

        // Device Instantiation

        Device device = house.createDevice("Fridge", "Fridgeratah V14", room);

        // Reading Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Reading reading3 = new Reading(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Reading reading5 = new Reading(15, time5);

        // List<Reading Configuration
        device.addReadingsToTheList(reading0);
        device.addReadingsToTheList(reading1);
        device.addReadingsToTheList(reading2);
        device.addReadingsToTheList(reading3);
        device.addReadingsToTheList(reading4);
        device.addReadingsToTheList(reading5);

        String expectedResult = "No valid values found for that period.\n";

        ctrl.getRoomByPosition(0);
        ctrl.getDeviceByPosition(0);

        LocalDateTime timeToTest0 = LocalDateTime.of(2019, 01, 26, 10, 00);
        LocalDateTime timeToTest1 = LocalDateTime.of(2019, 01, 30, 13, 00);

        //Act
        String result = ctrl.getDeviceDataSeriesToString(timeToTest0, timeToTest1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomDataSeriesToStringWithValidValues() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        house.addRoom(room);

        Device lamp = house.createDevice("Lamp", "Lamp1", room);

        Device fridge = house.createDevice("Fridge", "Fridge1", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        int position = 0;
        ctrl.getRoomByPosition(position);
        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 6.0 kWh\n" +
                "Date/hour: 2019-01-24 08:00, Energy Consumption: 10.0 kWh\n" +
                "Date/hour: 2019-01-24 16:00, Energy Consumption: 14.0 kWh\n";

        //Act
        String result = ctrl.getRoomDataSeriesToString(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomDataSeriesToStringWithNoValidValues() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        house.addRoom(room);

        Device lamp = house.createDevice("Lamp", "Lamp1", room);

        Device fridge = house.createDevice("Fridge", "Fridge1", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime startTime = LocalDateTime.of(2018, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 01, 25, 17, 40, 00);

        int position = 0;
        ctrl.getRoomByPosition(position);
        String expectedResult = "No valid values found for that period.\n";

        //Act
        String result = ctrl.getRoomDataSeriesToString(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetHouseGridDataSeriesToStringWithValidValues() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        grid.addRoom(room);
        house.addGrid(grid);

        house.addRoom(room);

        Device lamp = house.createDevice("Lamp", "Lamp1", room);

        Device fridge = house.createDevice("Fridge", "Fridge1", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 23, 55, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        int position = 0;
        ctrl.getHouseGridByPosition(position);
        String expectedResult = "Date/hour: 2019-01-24 08:00, Energy Consumption: 10.0 kWh\n" +
                "Date/hour: 2019-01-24 16:00, Energy Consumption: 14.0 kWh\n";

        //Act
        String result = ctrl.getHouseGridDataSeriesToString(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetHouseGridDataSeriesToStringWithNoValidValues() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);

        house.addRoom(room);

        Device lamp = house.createDevice("Lamp", "Lamp1", room);

        Device fridge = house.createDevice("Fridge", "Fridge1", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading3 = new Reading(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading4 = new Reading(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading5 = new Reading(7, time5);

        fridge.addReadingsToTheList(reading3);
        fridge.addReadingsToTheList(reading4);
        fridge.addReadingsToTheList(reading5);

        LocalDateTime startTime = LocalDateTime.of(2018, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 01, 25, 17, 40, 00);

        int position = 0;
        ctrl.getHouseGridByPosition(position);
        String expectedResult = "No valid values found for that period.\n";

        //Act
        String result = ctrl.getHouseGridDataSeriesToString(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);

        // Act
        boolean result = ctrl.houseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        //grid
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);
        // Act
        boolean result = ctrl.houseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        //act
        boolean result = ctrl.roomListIsEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        house.addRoom(room1);

        //act
        boolean result = ctrl.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, "room", -1, dim2);

        FridgeType fridgeType = new FridgeType();

        house.addRoom(room);
        house.addRoom(room2);

        house.createDevice("Dishwasher", "DeshWasher Teka", room2);

        house.createDevice("Fridge", "Fridge1", room2);

        // Act
        boolean result = ctrl.deviceListIsEmpty();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testCheckIfThereAreNoDevicesOnRoomsTrue() {
        // Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        // Act
        boolean result = ctrl.deviceListIsEmpty();

        // Assert
        assertTrue(result);
    }
}
