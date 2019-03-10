package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WineCoolerTest {
    private Room kitchen;
    private Room livingRoom;
    private Device wineCooler;
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
        livingRoom = new Room("LivingRoom", 1, dim);
        this.house.addRoom(kitchen);
        this.house.addRoom(livingRoom);

        // devices
        house.createDevice("WineCooler", "Awesome Wine Cooler", kitchen);

        // Freezer
        wineCooler = house.createDevice("WineCooler", "Even More Awesome Wine Cooler", kitchen);
        wineCooler.setAttributesDevType("Nominal Power", 90.0);
        wineCooler.setAttributesDevType("Number of Bottles", 36);
        wineCooler.setAttributesDevType("Annual Energy Consumption", 9000.0);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        wineCooler.addReadingsToTheList(reading0);
        wineCooler.addReadingsToTheList(reading1);
        wineCooler.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time0, 3.0);
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = wineCooler.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "Even More Awesome Wine Cooler";

        // Act
        String result = wineCooler.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "WineCooler";

        // act
        String result = wineCooler.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        //9000/365
        double expectedResult = 24.66;

        // Act
        double result = wineCooler.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> wineCooler.setName("Even More Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestWineCooler1() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Even More Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestWineCooler2() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> wineCooler.setName("Awesome Wine Cooler"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = wineCooler.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = wineCooler.setName("The Best Wine Cooler");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = wineCooler.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = wineCooler.setLocation(livingRoom);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Number of Bottles: 36\n" +
                "3 - Annual Energy Consumption: 9000.0\n";
        // Act
        String result = wineCooler.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Even More Awesome Wine Cooler\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";

        // Act
        String result = wineCooler.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeFalse() {
        //Act
        boolean result = wineCooler.setAttributesDevType("Refrigerator Capacity", 20);
        //Assert
        assertFalse(result);
    }


    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(wineCooler.getName());

        // Act
        int result = wineCooler.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = wineCooler.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = wineCooler.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Even More Awesome Wine Cooler, located in room: Kitchen\n";

        // Act
        String result = wineCooler.getNameToString();

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
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithThreeSolutionsTest() {
        // Arrange
        double expectedResult = 15;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 23, 23, 45, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = wineCooler.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setDeactivateDeviceTrue() {

        boolean result = wineCooler.setDeactivateDevice();
        assertTrue(result);
    }

    @Test
    public void setDeactivateDeviceAlreadyDeactivatedFalse() {

        wineCooler.setDeactivateDevice();
        boolean result = wineCooler.setDeactivateDevice();
        assertFalse(result);
    }

    @Test
    void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        wineCooler.setDeactivateDevice();
        // act
        LocalDateTime result = wineCooler.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        wineCooler.setDeactivateDevice();
        // act
        String result = wineCooler.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = wineCooler.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Assert
        wineCooler.setDeactivateDevice();

        // Act
        boolean result = wineCooler.getIsActive();

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
        Map<LocalDateTime, Double> result = wineCooler.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");
        expectedResult.add("Number of Bottles");
        expectedResult.add("Annual Energy Consumption");

        // Act
        List<String> result = wineCooler.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Assert
        double expectedResult = 90.0;

        // Act
        Object result = wineCooler.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Number of Bottles: 36\n" +
                "3 - Annual Energy Consumption: 9000.0\n";
        // Act
        String result = wineCooler.getSpecsToString();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<Reading> result = wineCooler.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void testGetNominalPowerTest() {
        //Arrange
        double expectedResult = 90.0;

        //Act
        double result = wineCooler.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testIfDeviceIsActiveTrue() {
        //Arrange
        //Act
        boolean result = wineCooler.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    void testIfDeviceIsActiveFalse() {
        //Arrange
        wineCooler.setDeactivateDevice();
        //Act
        boolean result = wineCooler.getIsActive();
        //Assert
        assertFalse(result);
    }

}
