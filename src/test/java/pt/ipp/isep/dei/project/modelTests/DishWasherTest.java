package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.dishwasher.DishWasherType;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
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

public class DishWasherTest {
    private Room kitchen;
    private Room laundry;
    private Device dishWasher;
    private House house;
    private Map<LocalDateTime, Double> map;
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;


    @BeforeEach
    public void StartUp() {
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);


        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", "room", 1, dim);
        this.laundry = new Room("Laundry", "room", 1, dim);

        house.addRoom(kitchen);
        house.addRoom(laundry);


        // devices
        house.createDevice("DishWasher", "Bosch 600 Series", kitchen);
        dishWasher = house.createDevice("DishWasher", "Bosch 500 Series", kitchen);
        dishWasher.setAttributesDevType("Capacity", 10);
        dishWasher.setAttributesDevType("Duration", 0);
        dishWasher.setAttributesDevType("Nominal Power", 1200);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        dishWasher.addReadingsToTheList(reading0);
        dishWasher.addReadingsToTheList(reading1);
        dishWasher.addReadingsToTheList(reading2);

        // Maps
        map = new TreeMap<>();
        map.put(time1, 5.0);
        map.put(time2, 7.0);
    }

    @Test
    public void getNominalPowerTest() {
        //Arrange
        double expectedResult = 1200.0;

        //Act
        double result = dishWasher.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = dishWasher.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameTest() {
        // Arrange
        String expectedResult = "Bosch 500 Series";

        // Act
        String result = dishWasher.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeTest() {
        // Arrange
        String expectedResult = "DishWasher";

        // act
        String result = dishWasher.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 0;

        // Act
        double result = dishWasher.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> dishWasher.setName("Bosch 500 Series"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> dishWasher.setName("Bosch 600 Series"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = dishWasher.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = dishWasher.setName("Bosch 700 Series");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationFalseTest() {
        // Act
        boolean result = dishWasher.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setLocationTrueTest() {
        // Act
        boolean result = dishWasher.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setLocationTrueTestNullValue() {
        // Act
        DishWasherType dishWasherType = new DishWasherType();
        Device maquina = dishWasherType.createDevice("nome");

        boolean result = maquina.setLocation(laundry);


        // Assert
        assertTrue(result);
    }

    @Test
    public void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Capacity: 10\n" +
                "2 - Nominal Power: 1200.0\n";
        // Act
        String result = dishWasher.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Bosch 500 Series\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = dishWasher.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void setSetAttributesDevTypeTrue() {
        //Act
        boolean result = dishWasher.setAttributesDevType("Capacity", 15);
        //Assert
        assertTrue(result);
    }

    @Test
    public void setSetAttributesDevTypeFalse() {
        //Act
        boolean result = dishWasher.setAttributesDevType("Capacity", 10);
        //Assert
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(dishWasher.getName());

        // Act
        int result = dishWasher.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = dishWasher.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = dishWasher.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Bosch 500 Series, located in room: Kitchen\n";

        // Act
        String result = dishWasher.getNameToString();

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
        double result = dishWasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 12;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 7, 45, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = dishWasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest2() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = dishWasher.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = dishWasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        dishWasher.setDeactivateDevice();
        // act
        LocalDateTime result = dishWasher.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getDateDeactivateDeviceToString() {
        // arrange
        String date = LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 5);
        dishWasher.setDeactivateDevice();
        // act
        String result = dishWasher.getDateDeactivateDeviceToString();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void getIsActiveTrueTest() {
        // Act
        boolean result = dishWasher.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getIsActiveFalseTest() {
        // arrange
        dishWasher.setDeactivateDevice();

        // Act
        boolean result = dishWasher.getIsActive();

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
        Map<LocalDateTime, Double> result = dishWasher.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = dishWasher.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeValueTest() {
        // Assert
        int expectedResult = 10;

        // Act
        Object result = dishWasher.getAttributeValue("Capacity");

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
        List<Reading> result = dishWasher.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetDeactivateDevice_true() {
        boolean expectedResult = true;
        boolean result = dishWasher.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDeactivateDevice_false() {
        boolean expectedResult = false;
        dishWasher.setDeactivateDevice();
        boolean result = dishWasher.setDeactivateDevice();
        assertEquals(expectedResult, result);
    }
}