package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.DishWasher;
import pt.ipp.isep.dei.project.model.ProgramList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherTest {
    @Test
    public void testGetTypeName() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        String expectedResult = "Dish Washer";

        //Act
        String result = dishWasher.getmTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        double expectedResult = 30;

        //Act
        double result = dishWasher.getmNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasher.setmCapacity(capacity);

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
        DishWasher dishWasher = new DishWasher(capacity1, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = dishWasher.setmCapacity(capacity2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasher.setmNominalPower(nominalPower);

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
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = dishWasher.setmNominalPower(nominalPower2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributesToString() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        String expectedResult = "1 - Capacity: 20\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = dishWasher.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        int attribute = 1;
        double value = 20.6;

        boolean expectedResult = true;
        //Act
        boolean result = dishWasher.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNonexistentFalse() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        int attribute = 3;
        double value = 20.6;
        dishWasher.setmCapacity(capacity);

        boolean expectedResult = false;

        //Act
        boolean result = dishWasher.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        int attribute = 2;
        double value = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = dishWasher.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        int expectedResult = 2;

        //Act
        int result = dishWasher.getNumberOfAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }
}