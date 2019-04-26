package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.stove.StoveType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StoveTest {

    private Room kitchen;
    private Room laundry;
    private Device stove2000;
    private Device stove3000;
    private House house;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;


    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", "room", 1, dim);
        this.laundry = new Room("Laundry", "room", 1, dim);

        house.addRoom(kitchen);
        house.addRoom(laundry);


        // devices
        stove2000 = house.createDevice("Stove", "Stove2000", kitchen);
        stove2000.setAttributesDevType("Nominal Power", 2000);
        stove3000 = house.createDevice("Stove", "Stove3000", kitchen);
        stove3000.setAttributesDevType("Nominal Power", 3000);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        stove2000.addReadingsToTheList(reading0);
        stove2000.addReadingsToTheList(reading1);
        stove2000.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @org.junit.jupiter.api.Test
    public void getNominalPowerTest() {
        //Arrange
        double expectedResult = 2000.0;

        //Act
        double result = stove2000.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @org.junit.jupiter.api.Test
    public void testGetLocation() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = stove2000.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetName() {
        // Arrange
        String expectedResult = "Stove2000";

        // Act
        String result = stove2000.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "Stove";

        // act
        String result = stove2000.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void setLocationFalseTest() {
        // Act
        boolean result = stove2000.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void setLocationTrueTest() {
        // Act
        boolean result = stove2000.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void setLocationTrueTestNullValue() {
        // Act
        StoveType type = new StoveType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = stove2000.getIsActive();

        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void getIsActiveFalseTest() {
        // arrange
        stove2000.setDeactivateDevice();

        // Act
        boolean result = stove2000.getIsActive();

        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetDeactivationDate_True() {
        // act
        boolean result = stove2000.setDeactivateDevice();
        // assert
        assertTrue(result);
    }

    @Test
    public void testGetDeactivationDate_False() {
        // arrange
        stove2000.setDeactivateDevice();
        // act
        boolean result = stove2000.setDeactivateDevice();
        // assert
        assertFalse(result);
    }
/*
    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> stove2000.setName("stove2000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> stove2000.setName("stove3000"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }*/

    @org.junit.jupiter.api.Test
    public void setNameFalseTest() {
        // Act
        boolean result = stove2000.setName("");

        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void setNameTrueTest() {
        // Act
        boolean result = stove2000.setName("stove4000");

        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = stove2000.equals(object);

        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testHashCode() {
        // Arrange
        int expectedResult = Objects.hash(stove2000.getName());

        // Act
        int result = stove2000.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }


    @org.junit.jupiter.api.Test
    public void testGetReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<Reading> result = stove2000.getReadings();

        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 2000.0\n";
        // Act
        String result = stove2000.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Stove2000\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = stove2000.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);

    }

    @org.junit.jupiter.api.Test
    public void setSetAttributesDevTypeTrue() {
        //Act
        boolean result = stove2000.setAttributesDevType("Nominal Power", 1500);
        //Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void setSetAttributesDevTypeFalse() {
        //Act
        boolean result = stove2000.setAttributesDevType("Nominal Power", "fh");
        //Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 1;

        // Act
        int result = stove2000.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Stove2000, located in room: Kitchen\n";

        // Act
        String result = stove2000.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        stove2000.setDeactivateDevice();
        // act
        String result = stove2000.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }

    @org.junit.jupiter.api.Test
    public void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = stove2000.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = stove2000.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }


    @org.junit.jupiter.api.Test
    public void getAttributeValueTest() {
        // Assert
        double expectedResult = 2000.0;

        // Act
        Object result = stove2000.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }
}