package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.fan.FanType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.*;

public class FanTest {
    private Room kitchen;
    private Room laundry;
    private Device fan;
    private House house;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;


    @Before
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", 1, dim);
        this.laundry = new Room("Laundry", 1, dim);

        house.addRoom(kitchen);
        house.addRoom(laundry);


        // devices
        house.createDevice("Fan", "Fan200", kitchen);
        fan = house.createDevice("Fan", "Fan300", kitchen);
        fan.setAttributesDevType("Time", 2);
        fan.setAttributesDevType("Nominal Power", 1200);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        fan.addReadingsToTheList(reading0);
        fan.addReadingsToTheList(reading1);
        fan.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    void getNominalPowerTest() {
        //Arrange
        double expectedResult = 1200.0;

        //Act
        double result = fan.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLocation() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = fan.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetName() {
        // Arrange
        String expectedResult = "Fan300";

        // Act
        String result = fan.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Fan";

        // act
        String result = fan.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = fan.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = fan.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationTrueTestNullValue() {
        // Act
        FanType fanType = new FanType();
        Device maquina = fanType.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = fan.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // arrange
        fan.setDeactivateDevice();

        // Act
        boolean result = fan.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetDeactivationDate_True() {
        // act
        boolean result = fan.setDeactivateDevice();
        // assert
        assertTrue(result);
    }

    @Test
    public void testGetDeactivationDate_False() {
        // arrange
        fan.setDeactivateDevice();
        // act
        boolean result = fan.setDeactivateDevice();
        // assert
        assertFalse(result);
    }

    /*@Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fan.setName("Fan300"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fan.setName("Fan200"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }*/

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = fan.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = fan.setName("Fan400");

        // Assert
        assertTrue(result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = fan.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Arrange
        int expectedResult = Objects.hash(fan.getName());

        // Act
        int result = fan.hashCode();

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
        List<Reading> result = fan.getReadings();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 1200.0\n";
        // Act
        String result = fan.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Fan300\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = fan.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void setSetAttributesDevTypeTrue() {
        //Act
        boolean result = fan.setAttributesDevType("Nominal Power", 15);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setSetAttributesDevTypeFalse() {
        //Act
        boolean result = fan.setAttributesDevType("Nominal Power", "fh");
        //Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 1;

        // Act
        int result = fan.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Fan300, located in room: Kitchen\n";

        // Act
        String result = fan.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        fan.setDeactivateDevice();
        // act
        String result = fan.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = fan.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = fan.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getAttributeValueTest() {
        // Assert
        double expectedResult = 1200.0;

        // Act
        Object result = fan.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecs(){
    }

    @Test
    public void getEnergyConsumptionInADayTest() {

    }


    @Test
    void getTotalEnergyConsumptionInAnIntervalWithoutSolutionsTest() {

    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {

    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest2() {

    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithThreeSolutionsTest() {

    }
}