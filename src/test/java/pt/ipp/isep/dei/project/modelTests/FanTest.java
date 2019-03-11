package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FanTest {
    private Room kitchen;
    private Room laundry;
    private Device fan;
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
        map.put(time0, 3.0);
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
    public void testGetSetDeactivationDate() {
        // arrange
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        fan.setDeactivateDevice();
        // act
        LocalDateTime result = fan.getDeactivationDate();
        // assert
        assertEquals(date, result);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fan.setName("Fan300"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> fan.setName("Fan200"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

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

    /*@Test
    public void testGetSpecs(){
    }

    @Test
    public void getEnergyConsumptionInADayTest() {

    }

    @Test
    void getDevSpecsAttributesToStringTest() {

    }

    @Test
    void getAttributesToStringTest() {

    }

    @Test
    public void setSetAttributesDevTypeTrue() {

    }

    @Test
    public void setSetAttributesDevTypeFalse() {

    }

    @Test
    void getNumberOfSpecsAttributesTest() {

    }

    @Test
    void getNameToStringTest() {

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

    @Test
    void getDateDeactivateDeviceToString() {

    }

    @Test
    void getDataSeriesTest() {

    }

    @Test
    void getSpecsListTest() {

    }

    @Test
    void getAttributeValueTest() {

    }*/

}