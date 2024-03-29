package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.television.TelevisionType;
import pt.ipp.isep.dei.project.model.house.Address;
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


public class TelevisionTest {
    private Room bedroom;
    private Room livingRoom;
    private Device television;
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
        Address address = new Address(null, null, null);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        // Rooms
        Dimension dim = new Dimension(2, 2, 3);
        bedroom = new Room("Bedroom", "room", 2, dim);
        livingRoom = new Room("LivingRoom", "room", 1, dim);
        this.house.addRoom(bedroom);
        this.house.addRoom(livingRoom);

        // devices
        house.createDevice("Television", "Flat Screen TV", bedroom);

        // Television
        television = house.createDevice("Television", "Smart TV", bedroom);
        television.setAttributesDevType("Nominal Power", 90.0);
        television.setAttributesDevType("Standby Power", 30.0);
        television.setAttributesDevType("Time", 120.0);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        television.addReadingsToTheList(reading0);
        television.addReadingsToTheList(reading1);
        television.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = bedroom;

        // Act
        Room result = television.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameTest() {
        // Arrange
        String expectedResult = "Smart TV";

        // Act
        String result = television.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "Television";

        // act
        String result = television.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        //nominal power * time
        double expectedResult = 10800;

        // Act
        double result = television.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.001);
    }

        @Test
        public void setNameWithSameNameTestSmartTV() {
            Throwable exception = assertThrows(RuntimeException.class, () -> television.setName("Smart TV"));
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

        @Test
        public void setNameWithSameNameTestFlatScreenTV() {
            Throwable exception = assertThrows(RuntimeException.class, () -> television.setName("Flat Screen TV"));
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

        @Test
        public void setNameWithSameLocationTestSmartTV() {
            Throwable exception = assertThrows(RuntimeException.class, () -> bedroom.getDeviceByPosition(0).setName("Smart TV"));
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

        @Test
        public void setNameWithSameLocationTestFlatScreenTV() {
            Throwable exception = assertThrows(RuntimeException.class, () -> bedroom.getDeviceByPosition(0).setName("Flat Screen TV"));
            assertEquals("Name already exists. Please write a new one.", exception.getMessage());
        }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = television.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueSamsungTV() {
        // Act
        boolean result = television.setName("Samsung TV");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest1() {
        // Act
        boolean result = television.setLocation(bedroom);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationFalseTest2() {
        // Act
        Room location = bedroom;
        boolean expectedResult = false;

        //Act
        boolean result = television.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = television.setLocation(livingRoom);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        TelevisionType type = new TelevisionType();
        Device maquina = type.createDevice("nome");

        boolean result = maquina.setLocation(livingRoom);


        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetLocation_AnotherLocation_True() {
        //Arrange
        Room location = livingRoom;
        boolean expectedResult = true;

        //Act
        boolean result = television.setLocation(location);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Standby Power: 30.0\n" +
                "3 - Time: 120.0\n";
        // Act
        String result = television.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Smart TV\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Bedroom\n";

        // Act
        String result = television.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setAttributesDevTypeFalse() {
        //Act
        boolean result = television.setAttributesDevType("Refrigerator Capacity", 20);
        //Assert
        assertFalse(result);
    }


    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(television.getName());

        // Act
        int result = television.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = television.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = television.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Smart TV, located in room: Bedroom\n";

        // Act
        String result = television.getNameToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithoutSolutionsTest() {
        // Arrange
        double expectedResult = 0;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 9, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 15, 00, 00);

        // Act
        double result = television.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = television.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithThreeSolutionsTest() {
        // Arrange
        double expectedResult = 15;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 23, 23, 45, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = television.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setDeactivateDeviceTrue() {

        boolean result = television.setDeactivateDevice();
        assertTrue(result);
    }

    @Test
    public void setDeactivateDeviceAlreadyDeactivatedFalse() {

        television.setDeactivateDevice();
        boolean result = television.setDeactivateDevice();
        assertFalse(result);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        television.setDeactivateDevice();
        // act
        LocalDateTime result = television.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        television.setDeactivateDevice();
        // act
        String result = television.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }


    @Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = television.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalseTest() {
        // Assert
        television.setDeactivateDevice();

        // Act
        boolean result = television.getIsActive();

        // Assert
        assertFalse(result);
    }

    @Test
    public void getDataSeriesTest() {
        // Assert
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> expectedResult = map;

        // Act
        Map<LocalDateTime, Double> result = television.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");
        expectedResult.add("Standby Power");
        expectedResult.add("Time");

        // Act
        List<String> result = television.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValueTest() {
        // Assert
        double expectedResult = 90.0;

        // Act
        Object result = television.getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetSpecsToString() {
        // Arrange
        String expectedResult = "1 - Nominal Power: 90.0\n" +
                "2 - Standby Power: 30.0\n" +
                "3 - Time: 120.0\n";
        // Act
        String result = television.getSpecsToString();
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
        List<Reading> result = television.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNominalPowerTest() {
        //Arrange
        double expectedResult = 90.0;

        //Act
        double result = television.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testIfDeviceIsActiveTrue() {
        //Arrange
        //Act
        boolean result = television.getIsActive();
        //Assert
        assertTrue(result);
    }

    @Test
    public void testIfDeviceIsActiveFalse() {
        //Arrange
        television.setDeactivateDevice();
        //Act
        boolean result = television.getIsActive();
        //Assert
        assertFalse(result);
    }

}
