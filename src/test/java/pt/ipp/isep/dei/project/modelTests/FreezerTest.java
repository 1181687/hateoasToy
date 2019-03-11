package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FreezerTest {
    private Room kitchen;
    private Room laundry;
    private Device freezer;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;
    private House house;

    @BeforeEach
    public void StartUp(){
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

        // devices
        house.createDevice("Freezer", "Miele Freezer Series 1000", kitchen);

        // Freezer
        freezer = house.createDevice("Freezer", "Miele Freezer Series 3500", kitchen);
        freezer.setAttributesDevType("Freezer Capacity", 40);
        freezer.setAttributesDevType("Annual Energy Consumption", 36500);
        freezer.setAttributesDevType("Nominal Power", 900);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        freezer.addReadingsToTheList(reading0);
        freezer.addReadingsToTheList(reading1);
        freezer.addReadingsToTheList(reading2);

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
        Room result = freezer.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "Miele Freezer Series 3500";

        // Act
        String result = freezer.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Freezer";

        // act
        String result = freezer.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 100;

        // Act
        double result = freezer.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> freezer.setName("Miele Freezer Series 3500"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestfreezer1() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Miele Freezer Series 3500"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameLocationTestfreezer2() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.getDeviceByPosition(0).setName("Miele Freezer Series 1000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> freezer.setName("Miele Freezer Series 1000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = freezer.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = freezer.setName("Miele PerfectCool Series 4000");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = freezer.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = freezer.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Freezer Capacity: 40.0\n" +
                "2 - Annual Energy Consumption: 36500.0\n" +
                "3 - Nominal Power: 900.0\n";
        // Act
        String result = freezer.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Miele Freezer Series 3500\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";

        // Act
        String result = freezer.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeFalse(){
        //Act
        boolean result= freezer.setAttributesDevType("Refrigerator Capacity", 20);
        //Assert
        assertFalse(result);
    }


    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(freezer.getName());

        // Act
        int result = freezer.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = freezer.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = freezer.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Miele Freezer Series 3500, located in room: Kitchen\n";

        // Act
        String result = freezer.getNameToString();

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
        double result = freezer.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = freezer.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = freezer.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setDeactivateDeviceTrue() {

        boolean result = freezer.setDeactivateDevice();
        assertTrue(result);
    }

    @Test
    public void setDeactivateDeviceAlreadyDeactivatedFalse() {

        freezer.setDeactivateDevice();
        boolean result = freezer.setDeactivateDevice();
        assertFalse(result);
    }

    @Test
    void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        freezer.setDeactivateDevice();
        // act
        LocalDateTime result = freezer.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        freezer.setDeactivateDevice();
        // act
        String result = freezer.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = freezer.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Assert
        freezer.setDeactivateDevice();

        // Act
        boolean result = freezer.getIsActive();

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
        Map<LocalDateTime, Double> result = freezer.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = freezer.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Assert
        double expectedResult = 900.0;

        // Act
        Object result = freezer.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Freezer Capacity: 40.0\n" +
                "2 - Annual Energy Consumption: 36500.0\n" +
                "3 - Nominal Power: 900.0\n";
        // Act
        String result = freezer.getSpecsToString();
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
        List<Reading> result = freezer.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void testGetNominalPowerTest() {
        //Arrange
        double expectedResult = 900.0;

        //Act
        double result = freezer.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testIfDeviceIsActiveTrue() {
        //Arrange
        //Act
        boolean result = freezer.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    void testIfDeviceIsActiveFalse() {
        //Arrange
        freezer.setDeactivateDevice();
        //Act
        boolean result = freezer.getIsActive();
        //Assert
        assertFalse(result);
    }

    /*@Test
    void testIfDeviceIsProgrammableFalse() {
        //Arrange
        //Act
        boolean result = freezer.isProgrammable();
        //Assert
        assertFalse(result);
    }

    @Test
    void testIfDeviceIsProgrammableReturnsFalseBecauseItsNotProgrammable() {
        //Arrange
        freezer.asProgrammable();
        //Act
        boolean result = freezer.isProgrammable();
        //Assert
        assertFalse(result);
    }*/
}