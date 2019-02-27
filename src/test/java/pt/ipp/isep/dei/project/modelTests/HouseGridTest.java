package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridTest {
    private House house;
    private HouseGrid mainGrid;
    private Room room1;
    private Room room2;
    private Room room3;
    private Device dev1;
    private Device dev2;
    private Device dev3;
    private Device dev6;
    private Map<LocalDateTime, Double> map;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        String houseGridName = "hg1";
        this.mainGrid = new HouseGrid(houseGridName);
        this.house.addGrid(mainGrid);

        Dimension dimensionRoom1 = new Dimension(5.2, 3.7, 8.5);
        room1 = new Room("Kid's room", 1, dimensionRoom1);
        Dimension dimensionRoom2 = new Dimension(5.2, 3.7, 8.5);
        room2 = new Room("Bathroom", 1, dimensionRoom2);
        Dimension dimensionRoom3 = new Dimension(5.2, 3.7, 8.5);
        room3 = new Room("Livingroom", 1, dimensionRoom3);

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name, type1);
        PowerSource powerSource2 = new PowerSource(name2, type1);
        mainGrid.addPowerSource(powerSource1);
        mainGrid.addPowerSource(powerSource2);

        //Fridge - dev1
        dev1 = house.createDevice("Fridge", "FridgeAriston", room1);
        dev1.setAttributesDevType("Freezer Capacity", 100);
        dev1.setAttributesDevType("Refrigerator Capacity", 100);
        dev1.setAttributesDevType("Annual Energy Consumption", 100);
        dev1.setAttributesDevType("Nominal Power", 100);

        //WashingMachine - dev2
        dev2 = house.createDevice("Washing Machine", "WashingMachineBosh", room1);
        dev2.setAttributesDevType("Capacity", 100);
        dev2.setAttributesDevType("Nominal Power", 100);

        //DishWasher - dev3
        dev3 = house.createDevice("DishWasher", "DishWasher1", room1);
        dev3.setAttributesDevType("Capacity", 100);
        dev3.setAttributesDevType("Nominal Power", 100);

        //Device dev4 = house.createDevice("Fridge", "FridgeSiemens", room2);
        //Device dev5 = house.createDevice("DishWasher", "DishWasherTeka", room2);

        //EWH - dev6
        dev6 = house.createDevice("Electric Water Heater", "ElectricWaterHeater1", room2);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);
        dev6.setAttributesDevType("Volume Of Water To Heat", 100);
        dev6.setAttributesDevType("Performance Ratio", 100);
        dev6.setAttributesDevType("Hot-Water Temperature", 100);

        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);

        dev1.addReadingsToTheList(reading0);
        dev1.addReadingsToTheList(reading1);
        dev1.addReadingsToTheList(reading2);

        dev3.addReadingsToTheList(reading0);
        dev3.addReadingsToTheList(reading1);
        dev3.addReadingsToTheList(reading2);

        dev6.addReadingsToTheList(reading0);
        dev6.addReadingsToTheList(reading1);

        map = new TreeMap<>();
        map.put(time0, 9.0);
        map.put(time1, 15.0);
        map.put(time2, 14.0);


    }


    @Test
    public void testHashCode() {
        //Arrange
        String name = "Main Grid";
        HouseGrid grid = new HouseGrid(name);
        int expectedResult = Objects.hash(name);

        //Act
        int result = grid.hashCode();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashCodeWithDifferentName() {
        //Arrange
        String name1 = "Main Grid";
        String name2 = "Secondary Grid";

        HouseGrid grid = new HouseGrid(name1);
        int expectedResult = Objects.hash(name2);

        //Act
        int result = grid.hashCode();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testEqualsWithDifferentObjects() {
        //Arrange
        HouseGrid grid = new HouseGrid("Main Grid");
        Dimension dimension = new Dimension(2, 2, 2);
        //Act
        boolean result = grid.equals(dimension);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testDisplayRoomsAttachedToHouseGrid() {
        // Arrange
        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n" +
                        "2- Name: Bathroom, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n" +
                        "3- Name: Livingroom, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n";
        // Act
        String result = mainGrid.getRoomListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Act
        boolean result = mainGrid.getRoomList().getListOfRooms().contains(room1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testListPowerSources() {
        //Arrange
        String expectedResult = "1- Power Source 1\n" +
                "2- Power Source 2\n";
        //Act
        String result = mainGrid.getPowerSourceListContent();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesList() {
        //Arrange
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(dev1);
        expectedResult.add(dev2);
        expectedResult.add(dev6);

        List<Device> result = mainGrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesListEmptylist() {


        List<Device> expectedResult = new ArrayList<>();

        List<Device> result = mainGrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPower() {
        //Assert

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);

        double expectedResult = 400;

        //Act
        double result = mainGrid.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getRoomFromAPosition() {
        //arrange

        mainGrid.addRoom(room1);
        mainGrid.addRoom(room2);

        Room expectResult = room1;

        //act
        Room result = mainGrid.getRoomByPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        /*String houseGridName = "hgname1";
        HouseGrid houseGrid = new HouseGrid(houseGridName);

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        houseGrid.addRoom(room);

        //initiate Devices
        Device dev = house.createDevice("Fridge","Fridge1", room);


        Device dev1 = house.createDevice("Lamp","Lamp1", room);*/

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);

        String expectedResult =
                "1 - Name of the device: FridgeAriston\n" +
                        "2 - Name of the device: WashingMachineBosh\n" +
                        "3 - Name of the device: DishWasher1\n";

        // Act
        String result = mainGrid.getDeviceListContent(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {
        // Arrange

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);

        int expectResult = 3;
        //act
        int result = mainGrid.getDeviceListSizeByRoomPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //act
        boolean result = mainGrid.isRoomListEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        //act
        boolean result = mainGrid.isRoomListEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        this.mainGrid.addRoom(room3);

        // Act
        boolean result = mainGrid.isDeviceListOfAllRoomsEmpty();
        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        // Act
        boolean result = mainGrid.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceFromPositionInList() {

        //Arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        Device expectedResult = dev1;

        // Act
        Device result = mainGrid.getDeviceByRoomAndDevicePosition(0, 0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);


        int expectResult = 3;
        //act
        int result = mainGrid.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }


    @Test
    public void getListSizeEmptyList() {
        //arrange
        int expectResult = 0;
        //act
        int result = mainGrid.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testCheckThereAreNoDevices() {
        //Act

        boolean result = mainGrid.isDeviceListOfAllRoomsEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testCheckThereAreNoDevicesWithDevices() {
        //arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        boolean result = mainGrid.isDeviceListOfAllRoomsEmpty();

        //Assert
        assertFalse(result);
    }

    @Test
    public void testValidateNameWithEmptyNameShouldThrowException() {
        //Arrange
        String name = " ";

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );
        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testValidateNameWithNullNameShouldThrowException() {
        //Arrange
        String name = null;

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );
        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyNameShouldThrowException() {
        //Arrange
        String name = "";

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullNameShouldThrowException() {
        //Arrange
        String name = null;

        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(name)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testGetNameToString() {
        // Arrange
        String expectResult = "HouseGrid: mainGrid\n";
        //act
        String result = mainGrid.getNameToString();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithThreeValidReadingss() {
        //Arrange
        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        double expectedResult = 12;
        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithOneValidReadings() {
        //Arrange

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 24, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        double expectedResult = 0;
        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoValidReadingss() {

        //Arrange

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 25, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        double expectedResult = 0;

        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithTwoRooms() {
        //Arrange

        this.mainGrid.addRoom(room1);
        this.mainGrid.addRoom(room2);
        this.mainGrid.addRoom(room3);

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        double expectedResult = 29;

        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoRoomsConnected() {

        //Arrange

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        double expectedResult = 0;

        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);


        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDataSeries() {

        //Arrange

        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        //Act
        Map<LocalDateTime, Double> result = mainGrid.getDataSeries(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result);
    }
}
