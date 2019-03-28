package pt.ipp.isep.dei.project.modelTests;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseGridTest {
    private House house;
    private HouseGrid mainGrid;
    private HouseGrid secondaryGrid;
    private Room kidsRoom;
    private Room bathroom;
    private Room livingRoom;
    private Device fridge;
    private Device washingMachine;
    private Device dishwasher;
    private Device electricWaterHeater;
    private Map<LocalDateTime, Double> map;

    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // House Grids
        this.mainGrid = new HouseGrid("Main grid");
        this.secondaryGrid = new HouseGrid("Secondary grid");
        this.house.addGrid(mainGrid);

        // Rooms
        Dimension dimensionRoom = new Dimension(5.2, 3.7, 8.5);
        kidsRoom = new Room("Kid's room", 1, dimensionRoom);
        bathroom = new Room("Bathroom", 1, dimensionRoom);
        livingRoom = new Room("Living Room", 1, dimensionRoom);
        this.mainGrid.addRoom(kidsRoom);
        this.mainGrid.addRoom(bathroom);
        this.mainGrid.addRoom(livingRoom);

        // Power Source
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name, type1);
        PowerSource powerSource2 = new PowerSource(name2, type1);
        mainGrid.addPowerSource(powerSource1);
        mainGrid.addPowerSource(powerSource2);

        // devices
        fridge = house.createDevice("Fridge", "Miele PerfectCool Series 3500", kidsRoom);
        fridge.setAttributesDevType("Freezer Capacity", 100);
        fridge.setAttributesDevType("Refrigerator Capacity", 100);
        fridge.setAttributesDevType("Annual Energy Consumption", 100);
        fridge.setAttributesDevType("Nominal Power", 100);

        washingMachine = house.createDevice("WashingMachine", "Maytag 3.6", kidsRoom);
        washingMachine.setAttributesDevType("Capacity", 100);
        washingMachine.setAttributesDevType("Nominal Power", 100);

        dishwasher = house.createDevice("DishWasher", "Bosch 500 Series", kidsRoom);
        dishwasher.setAttributesDevType("Capacity", 100);
        dishwasher.setAttributesDevType("Nominal Power", 100);

        electricWaterHeater = house.createDevice("ElectricWaterHeater", "Bosch Tronic 3000", bathroom);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 100);
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 100);
        electricWaterHeater.setAttributesDevType("Nominal Power", 100);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 100);

        // Readings
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);
        fridge.addReadingsToTheList(reading0);
        fridge.addReadingsToTheList(reading1);
        fridge.addReadingsToTheList(reading2);
        dishwasher.addReadingsToTheList(reading0);
        dishwasher.addReadingsToTheList(reading1);
        dishwasher.addReadingsToTheList(reading2);
        electricWaterHeater.addReadingsToTheList(reading0);
        electricWaterHeater.addReadingsToTheList(reading1);

        // Map
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
                        "3- Name: Living Room, House Floor: 1, Dimension - Height: 5.2, Length: 3.7, Width: 8.5\n";
        // Act
        String result = mainGrid.getRoomListContent();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Act
        boolean result = mainGrid.getRoomList().getListOfRooms().contains(kidsRoom);

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
        expectedResult.add(fridge);
        expectedResult.add(washingMachine);
        expectedResult.add(dishwasher);
        expectedResult.add(electricWaterHeater);

        List<Device> result = mainGrid.getAllDevicesList();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDevicesListEmptylist() {
        // Arrange
        List<Device> expectedResult = new ArrayList<>();

        // Act
        List<Device> result = secondaryGrid.getAllDevicesList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPower() {
        //Assert
        double expectedResult = 400;

        //Act
        double result = mainGrid.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getRoomFromAPosition() {
        //arrange
        Room expectResult = kidsRoom;

        //act
        Room result = mainGrid.getRoomByPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDeviceListContentTest() {
        // Arrange
        String expectedResult =
                "1 - Name of the device: Miele PerfectCool Series 3500\n" +
                        "2 - Name of the device: Maytag 3.6\n" +
                        "3 - Name of the device: Bosch 500 Series\n";

        // Act
        String result = mainGrid.getDeviceListContent(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {
        // Arrange
        int expectResult = 3;
        //act
        int result = mainGrid.getDeviceListSizeByRoomPosition(0);
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        //arrange
        String houseGridName = "hg2";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        this.house.addGrid(houseGrid1);

        //act
        boolean result = houseGrid1.isRoomListEmpty();
        //assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange
        //act
        boolean result = mainGrid.isRoomListEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void checkIfDeviceListIsEmptyTestTrue() {
        // Arrange
        String houseGridName = "hg2";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        this.house.addGrid(houseGrid1);

        // Act
        boolean result = houseGrid1.isDeviceListOfAllRoomsEmpty();
        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange

        // Act
        boolean result = mainGrid.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDeviceFromPositionInList() {

        //Arrange

        Device expectedResult = fridge;

        // Act
        Device result = mainGrid.getDeviceByRoomAndDevicePosition(0, 0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange

        int expectResult = 3;
        //act
        int result = mainGrid.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }


    @Test
    public void getListSizeEmptyList() {
        //arrange

        String houseGridName = "hg2";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        this.house.addGrid(houseGrid1);

        int expectResult = 0;
        //act
        int result = houseGrid1.getRoomListSize();
        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testCheckThereAreNoDevices() {
        //arrange
        String houseGridName = "hg2";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        this.house.addGrid(houseGrid1);

        //Act
        boolean result = houseGrid1.isDeviceListOfAllRoomsEmpty();

        //Assert
        assertTrue(result);
    }

    @Test
    public void testCheckThereAreNoDevicesWithDevices() {
        //arrange
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
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(null)
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
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                new HouseGrid(null)
        );

        //Assert
        assertEquals("Please enter a valid name. Name should not be empty", exception.getMessage());
    }

    @Test
    public void testGetNameToString() {
        // Arrange
        String expectResult = "housegrid: Main grid\n";

        //act
        String result = mainGrid.getNameToString();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithThreeValidReadingss() {
        //Arrange
        LocalDateTime startTime = LocalDateTime.of(2019, 01, 23, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 17, 40, 00);

        double expectedResult = 38;
        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithOneValidReadings() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 24, 16, 40, 00);

        double expectedResult = 0;
        //Act
        double result = mainGrid.getEnergyConsumptionInAnInterval(startTime, endTime);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetEnergyConsumptionInAnIntervalWithNoValidReadingss() {

        //Arrange
        LocalDateTime startTime = LocalDateTime.of(2019, 01, 25, 15, 20, 00);
        LocalDateTime endTime = LocalDateTime.of(2019, 01, 25, 17, 40, 00);

        double expectedResult = 0;

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
        double result = secondaryGrid.getEnergyConsumptionInAnInterval(startTime, endTime);


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

    @Test
    public void getReadings(){
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Reading reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Reading reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Reading reading2 = new Reading(7, time2);
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);
        expectedResult.add(reading0);
        expectedResult.add(reading1);

        //Act
        List<Reading> result = mainGrid.getReadings();
        //Assert
        assertEquals(expectedResult,result);

    }
}
*/