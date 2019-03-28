package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.lamp.LampType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.*;

class LampTest {
    private Room kitchen;
    private Room laundry;
    private Device lamp;
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
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);
        this.house.addRoom(kitchen);
        this.house.addRoom(laundry);

        // devices
        Device dummyLamp = house.createDevice("Lamp", "TaoTronics Elune TT-DL01", kitchen);
        lamp = house.createDevice("Lamp", "TaoTronics Elune TT-DL02", kitchen);
        lamp.setAttributesDevType("Luminous Flux", 2800);
        lamp.setAttributesDevType("Time", 1);
        lamp.setAttributesDevType("Nominal Power", 300);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        lamp.addReadingsToTheList(reading0);
        lamp.addReadingsToTheList(reading1);
        lamp.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
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
        List<Reading> result = lamp.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void getNominalPowerTest() {
        //Arrange
        double expectedResult = 300.0;

        //Act
        double result = lamp.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = lamp.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "TaoTronics Elune TT-DL02";

        // Act
        String result = lamp.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Lamp";

        // act
        String result = lamp.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 300.0;

        // Act
        double result = lamp.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    /*@Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> lamp.setName("TaoTronics Elune TT-DL02"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameWithSameNameAndInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> kitchen.addDevice(lamp));
        assertEquals("Device with same name is already in the roomList", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> lamp.setName("TaoTronics Elune TT-DL01"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }*/

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = lamp.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = lamp.setName("Miele PerfectCool Series 4000");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = lamp.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = lamp.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationTrueTestNullValue() {
        // Act
        LampType type = new LampType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Luminous Flux: 2800.0\n" +
                "2 - Nominal Power: 300.0\n";
        // Act
        String result = lamp.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: TaoTronics Elune TT-DL02\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = lamp.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributesDevTypeTrue(){
        //Act
        double value = 2600.5;
        boolean result= lamp.setAttributesDevType("Luminous Flux", value);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributesDevTypeFalse(){
        //Act
        boolean result= lamp.setAttributesDevType("Luminous Flux", 2800);
        //Assert
        assertFalse(result);
    }

    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(lamp.getName());

        // Act
        int result = lamp.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = lamp.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = lamp.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: TaoTronics Elune TT-DL02, located in room: Kitchen\n";

        // Act
        String result = lamp.getNameToString();

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
        double result = lamp.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = lamp.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = lamp.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        lamp.setDeactivateDevice();
        // act
        LocalDateTime result = lamp.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        lamp.setDeactivateDevice();
        // act
        String result = lamp.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = lamp.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Arrange
        lamp.setDeactivateDevice();

        // Act
        boolean result = lamp.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    void getDataSeriesTest() {
        // Arrange
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = lamp.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = lamp.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Arrange
        double expectedResult = 300.0;

        // Act
        Object result = lamp.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Luminous Flux: 2800.0\n" +
                "2 - Nominal Power: 300.0\n";
        // Act
        String result = lamp.getSpecsToString();
        // Assert
        assertEquals(expectedResult, result);
    }
}