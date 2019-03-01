package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTest {
    private Room kitchen;
    private Room laundry;
    private Device fridge;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;
    private House house;


    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);
        this.house.addRoom(kitchen);
        this.house.addRoom(laundry);

        // Devices
        // Dummy Fridge
        house.createDevice("Fridge", "Miele PerfectCool Series 1000", kitchen);

        // Fridge
        fridge = house.createDevice("Fridge", "Miele PerfectCool Series 3500", kitchen);
        fridge.setAttributesDevType("Freezer Capacity", 40);
        fridge.setAttributesDevType("Refrigerator Capacity", 20);
        fridge.setAttributesDevType("Annual Energy Consumption", 36500);
        fridge.setAttributesDevType("Nominal Power", 900);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        fridge.addReadingsToTheList(reading0);
        fridge.addReadingsToTheList(reading1);
        fridge.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time0, 3.0);
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<Reading> result = fridge.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void getNominalPowerTest() {
        //Arrange
        double expectedResult = 900.0;

        //Act
        double result = fridge.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = fridge.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "Miele PerfectCool Series 3500";

        // Act
        String result = fridge.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Fridge";

        // act
        String result = fridge.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 100;

        // Act
        double result = fridge.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fridge.setName("Miele PerfectCool Series 3500"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestFridge1() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Miele PerfectCool Series 3500"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestFridge2() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Miele PerfectCool Series 1000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fridge.setName("Miele PerfectCool Series 1000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = fridge.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = fridge.setName("Miele PerfectCool Series 4000");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = fridge.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = fridge.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Freezer Capacity: 40.0\n" +
                "2 - Refrigerator Capacity: 20.0\n" +
                "3 - Annual Energy Consumption: 36500.0\n" +
                "4 - Nominal Power: 900.0\n";
        // Act
        String result = fridge.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Miele PerfectCool Series 3500\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";

        // Act
        String result = fridge.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeTrue(){
        //Act
        boolean result = fridge.setAttributesDevType("Refrigerator Capacity", 25);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setAttributesDevTypeFalse(){
        //Act
        boolean result= fridge.setAttributesDevType("Refrigerator Capacity", 20);
        //Assert
        assertFalse(result);
    }


    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(fridge.getName());

        // Act
        int result = fridge.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = fridge.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 4;

        // Act
        int result = fridge.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Miele PerfectCool Series 3500, located in room: Kitchen\n";

        // Act
        String result = fridge.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithoutSolutionsTest() {
        // Arrange
        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = fridge.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = fridge.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithTwoSolutionsTest() {
        // Arrange
        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 0, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = fridge.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setDeactivateDeviceTrue() {

        boolean result = fridge.setDeactivateDevice();
        assertTrue(result);
    }

    @Test
    public void setDeactivateDeviceAlreadyDeactivatedFalse() {

        fridge.setDeactivateDevice();
        boolean result = fridge.setDeactivateDevice();
        assertFalse(result);
    }

    @Test
    void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        fridge.setDeactivateDevice();
        // act
        LocalDateTime result = fridge.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        fridge.setDeactivateDevice();
        // act
        String result = fridge.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = fridge.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Assert
        fridge.setDeactivateDevice();

        // Act
        boolean result = fridge.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = fridge.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Refrigerator Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = fridge.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Assert
        double expectedResult = 900.0;

        // Act
        Object result = fridge.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Freezer Capacity: 40.0\n" +
                "2 - Refrigerator Capacity: 20.0\n" +
                "3 - Annual Energy Consumption: 36500.0\n" +
                "4 - Nominal Power: 900.0\n";
        // Act
        String result = fridge.getSpecsToString();
        // Assert
        assertEquals(expectedResult, result);
    }
}
