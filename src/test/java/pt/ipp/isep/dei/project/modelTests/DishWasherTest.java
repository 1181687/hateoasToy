package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.DishWasher;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DishWasherTest {
    private Room kitchen;
    private Room laundry;
    private DishWasher dishwasher;


    @BeforeEach
    public void StartUp() {
        // Rooms
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        laundry = new Room("Laundry", 1, dim);

        // Devices
        DishWasher dummywasher = new DishWasher("Bosch 400 Series", kitchen);
        dishwasher = new DishWasher("Bosch 500 Series", kitchen);
        dishwasher.setAttributesDevType("Capacity", 10);
        dishwasher.setAttributesDevType("Duration", 0);
        dishwasher.setAttributesDevType("Nominal Power", 1200);
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
    void testSetLocationTrueTest() {
        // Act
        boolean result = dishwasher.setLocation(laundry);

        // Assert
        assertTrue(result);
    }

    @Test
    void getDevSpecsAttributesToString() {
    }

    @Test
    void getAttributesToString() {
    }

    @Test
    void setAttributesDevType() {
    }

    @Test
    void hashCodeTest() {
        //Arrange
        int expectedResult = Objects.hash(dishwasher.getName());

        // Act
        int result = dishwasher.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void equals() {
    }

    @Test
    void getNumberOfSpecsAttributes() {
    }

    @Test
    void getNameToString() {
    }

    @Test
    void addReadingsToTheList() {
    }

    @Test
    void getSumOfTheReadings() {
    }

    @Test
    void getReadingsListInInterval() {
    }

    @Test
    void getEnergyConsumptionInAnInterval() {
    }

    @Test
    void setDeactivateDevice() {
    }

    @Test
    void getIsActive() {
    }

    @Test
    void getDataSeries() {
    }

    @Test
    void getSpecs() {
    }
}