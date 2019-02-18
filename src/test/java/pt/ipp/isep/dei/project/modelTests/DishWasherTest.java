package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.DishWasher;
import pt.ipp.isep.dei.project.model.Readings;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DishWasherTest {
    private Room kitchen;
    private Room laundry;
    private DishWasher dishwasher;
    private Map<LocalDateTime, Double> map;

    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);

        // Devices
        DishWasher dummyWasher = new DishWasher("Bosch 400 Series", kitchen);
        dishwasher = new DishWasher("Bosch 500 Series", kitchen);
        dishwasher.setAttributesDevType("Capacity", 10);
        dishwasher.setAttributesDevType("Duration", 0);
        dishwasher.setAttributesDevType("Nominal Power", 1200);

        // Readings
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        Readings readings0 = new Readings(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        Readings readings1 = new Readings(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        Readings readings2 = new Readings(7, time2);
        dishwasher.addReadingsToTheList(readings0);
        dishwasher.addReadingsToTheList(readings1);
        dishwasher.addReadingsToTheList(readings2);

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
        double result = dishwasher.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getLocationTest() {
        // Arrange
        Room expectedResult = kitchen;

        // Act
        Room result = dishwasher.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTest() {
        // Arrange
        String expectedResult = "Bosch 500 Series";

        // Act
        String result = dishwasher.getName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeTest() {
        // Arrange
        String expectedResult = "Dishwasher";

        // act
        String result = dishwasher.getType();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionInADayTest() {
        // Arrange
        double expectedResult = 0;

        // Act
        double result = dishwasher.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void setNameWithSameNameTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> dishwasher.setName("Bosch 500 Series"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameAlreadyInListTest() {
        Throwable exception = assertThrows(RuntimeException.class, () -> dishwasher.setName("Bosch 400 Series"));
        assertEquals("Name already exists. Please write a new one.", exception.getMessage());
    }

    @Test
    public void setNameFalseTest() {
        // Act
        boolean result = dishwasher.setName("");

        // Assert
        assertTrue(result);
    }

    @Test
    public void setNameTrueTest() {
        // Act
        boolean result = dishwasher.setName("Bosch 600 Series");

        // Assert
        assertTrue(result);
    }

    @Test
    void setLocationFalseTest() {
        // Act
        boolean result = dishwasher.setLocation(kitchen);

        // Assert
        assertFalse(result);
    }

    @Test
    void setLocationTrueTest() {
        // Act
        boolean result = dishwasher.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Capacity: 10\n" +
                "2 - Nominal Power: 1200.0\n";
        // Act
        String result = dishwasher.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributesToStringTest() {
        // Arrange
        String expectedResult = "1 - Name: Bosch 500 Series\n" +
                "2 - Device Specifications \n" +
                "3 - Location: Kitchen\n";
        // Act
        String result = dishwasher.getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void hashCodeTest() {
        // Arrange
        int expectedResult = Objects.hash(dishwasher.getName());

        // Act
        int result = dishwasher.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equalsDifferentObjectTest() {
        // Arrange
        Object object = new Object();

        // Act
        boolean result = dishwasher.equals(object);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNumberOfSpecsAttributesTest() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = dishwasher.getNumberOfSpecsAttributes();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameToStringTest() {
        // Arrange
        String expectedResult = "Device: Bosch 500 Series, located in room: Kitchen\n";

        // Act
        String result = dishwasher.getNameToString();

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
        double result = dishwasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 7, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = dishwasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getTotalEnergyConsumptionInAnIntervalWithOneSolutionTest2() {
        // Arrange
        double expectedResult = 7;

        LocalDateTime startDate = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        // Act
        double result = dishwasher.getEnergyConsumptionInAnInterval(startDate, endDate);

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
        double result = dishwasher.getEnergyConsumptionInAnInterval(startDate, endDate);

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    void getIsActiveTrueTest() {
        // Act
        boolean result = dishwasher.getIsActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void getIsActiveFalseTest() {
        // Assert
        dishwasher.setDeactivateDevice();

        // Act
        boolean result = dishwasher.getIsActive();

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
        Map<LocalDateTime, Double> result = dishwasher.getDataSeries(time0, time2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSpecsListTest() {
        // Assert
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = dishwasher.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getAttributeValueTest() {
        // Assert
        int expectedResult = 10;

        // Act
        Object result = dishwasher.getAttributeValue("Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }
}