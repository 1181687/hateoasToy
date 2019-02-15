package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDataSeriesController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class GetEnergyConsumptionDataSeriesControllerTest {
    private GetEnergyConsumptionDataSeriesController ctrl;
    private House house;
    private HouseGrid houseGrid;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        house.setAddress(address);
        house.setInsertedGeoArea(insertedGeoArea);

        this.ctrl = new GetEnergyConsumptionDataSeriesController(house);
    }

    @Test
    public void testGetHouseGridListToString() {
        // Arrange
        //grid
        String gridName = "Grid";
        houseGrid = new HouseGrid(gridName);
        house.addGrid(houseGrid);

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
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

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
        Room room0 = new Room("Kitchen", 1, dim);
        Room room1 = new Room("Laundry", 2, dim);

        // FridgeSpecs Instantiation

        // ElectricWaterHeaterSpecs Instantiation

        // Device Instantiation
        FridgeType fridgeType = new FridgeType();
        WashingMachineType washingMachineType = new WashingMachineType();

        Device device0 = fridgeType.createDevice("Fridgeratah V14", room0);
        Device device1 = washingMachineType.createDevice("Bosch Tronic 3000", room1);

        house.addRoom(room0);
        house.addRoom(room1);

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
        houseGrid = new HouseGrid(gridName);
        house.addGrid(houseGrid);

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
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, houseFloor2, dimension2);

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
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

        //initiate Devices
        FridgeType fridgeType = new FridgeType();
        Device dev = fridgeType.createDevice("Fridge1", room1);

        LampType lampType = new LampType();
        Device dev1 = lampType.createDevice("Lamp1", room1);

        house.addRoom(room1);

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
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        int expectResult = 0;
        //act
        int result = ctrl.getDeviceListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    void testGetDeviceDataSeriesToString() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        // FridgeType Instantiation
        FridgeType fridgeType = new FridgeType();

        // Device Instantiation
        Device device = fridgeType.createDevice("Fridgeratah V14", room);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Readings readings3 = new Readings(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Readings readings5 = new Readings(15, time5);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);
        device.addReadingsToTheList(readings3);
        device.addReadingsToTheList(readings4);
        device.addReadingsToTheList(readings5);

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
    void testGetDeviceDataSeriesToStringNoValidNumbers() {
        //Arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        // FridgeType Instantiation
        FridgeType fridgeType = new FridgeType();

        // Device Instantiation
        Device device = fridgeType.createDevice("Fridgeratah V14", room);

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        LocalDateTime time3 = LocalDateTime.of(2019, 01, 25, 10, 00, 00);
        Readings readings3 = new Readings(10, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 25, 12, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 25, 16, 00, 00);
        Readings readings5 = new Readings(15, time5);

        // List<Readings Configuration
        device.addReadingsToTheList(readings0);
        device.addReadingsToTheList(readings1);
        device.addReadingsToTheList(readings2);
        device.addReadingsToTheList(readings3);
        device.addReadingsToTheList(readings4);
        device.addReadingsToTheList(readings5);

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
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

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
        Room room = new Room("Room", 2, dim);

        house.addRoom(room);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

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
        Room room = new Room("Room", 2, dim);

        //grid
        String gridName = "Grid";
        houseGrid = new HouseGrid(gridName);
        house.addGrid(houseGrid);

        houseGrid.attachRoom(room);

        house.addRoom(room);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        int position = 0;
        ctrl.getHouseGridByPosition(position);
        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 6.0 kWh\n" +
                "Date/hour: 2019-01-24 08:00, Energy Consumption: 10.0 kWh\n" +
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
        Room room = new Room("Room", 2, dim);

        //grid
        String gridName = "Grid";
        houseGrid = new HouseGrid(gridName);
        house.addGrid(houseGrid);

        house.addRoom(room);

        houseGrid.attachRoom(room);

        LampType lampType = new LampType();
        Device lamp = lampType.createDevice("LampSpecs", room);

        FridgeType fridgeType = new FridgeType();
        Device fridge = fridgeType.createDevice("FridgeSpecs", room);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);

        lamp.addReadingsToTheList(readings0);
        lamp.addReadingsToTheList(readings1);
        lamp.addReadingsToTheList(readings2);

        LocalDateTime time3 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings3 = new Readings(3, time3);
        LocalDateTime time4 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings4 = new Readings(5, time4);
        LocalDateTime time5 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings5 = new Readings(7, time5);

        fridge.addReadingsToTheList(readings3);
        fridge.addReadingsToTheList(readings4);
        fridge.addReadingsToTheList(readings5);

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
        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
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
        houseGrid = new HouseGrid(gridName);
        house.addGrid(houseGrid);
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
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, houseFloor1, dimension1);

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
        Room room = new Room("Room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, -1, dim2);

        FridgeType fridgeType = new FridgeType();
        DishWasherType dishWasherType = new DishWasherType();
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();

        Device dev4 = fridgeType.createDevice("FridgeSiemens", room2);
        Device dev5 = dishWasherType.createDevice("DishWasherTeka", room2);
        Device dev6 = electricWaterHeaterType.createDevice("ElectricWaterHeaterSpecs", room2);

        house.addRoom(room);
        house.addRoom(room2);

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
        Room room = new Room("Room", 2, dim);

        // Act
        boolean result = ctrl.deviceListIsEmpty();

        // Assert
        assertTrue(result);
    }

}
