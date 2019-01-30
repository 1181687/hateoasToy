package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.DishWasherSpecs;
import pt.ipp.isep.dei.project.model.ProgramList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherSpecsTest {
    @Test
    public void testGetTypeName() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        String expectedResult = "Dish Washer";

        //Act
        String result = dishWasherSpecs.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        double expectedResult = 30;

        //Act
        double result = dishWasherSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasherSpecs.setCapacity(capacity);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityTrue() {
        //Arrange
        int capacity1 = 20;
        int capacity2 = 30;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity1, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = dishWasherSpecs.setCapacity(capacity2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasherSpecs.setNominalPower(nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerTrue() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        double nominalPower2 = 23;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = dishWasherSpecs.setNominalPower(nominalPower2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributesToString() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);

        String expectedResult = "1 - Capacity: 20\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = dishWasherSpecs.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);
        int attribute = 1;
        double value = 20.6;

        boolean expectedResult = true;
        //Act
        boolean result = dishWasherSpecs.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNonexistentFalse() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);
        int attribute = 3;
        double value = 20.6;
        dishWasherSpecs.setCapacity(capacity);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasherSpecs.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);
        int attribute = 2;
        double value = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = dishWasherSpecs.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasherSpecs dishWasherSpecs = new DishWasherSpecs(capacity, nominalPower, programList);
        int expectedResult = 2;

        //Act
        int result = dishWasherSpecs.getNumberOfAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }
}