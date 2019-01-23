package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ProgramList;
import pt.ipp.isep.dei.project.model.WashingMachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineTest {

    @Test
    public void testGetTypeName() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        String expectedResult = "Washing Machine";

        //Act
        String result = washingMachine.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        double expectedResult = 30;

        //Act
        double result = washingMachine.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = washingMachine.setCapacity(capacity);

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
        WashingMachine washingMachine = new WashingMachine(capacity1, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = washingMachine.setCapacity(capacity2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerFalse() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        boolean expectedResult = false;

        //Act
        boolean result = washingMachine.setNominalPower(nominalPower);

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
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        boolean expectedResult = true;

        //Act
        boolean result = washingMachine.setNominalPower(nominalPower2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributesToString() {
        //Arrange
        double capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);

        String expectedResult = "1 - Capacity: 20.0\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = washingMachine.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        int attribute = 1;
        double value = 20.6;

        boolean expectedResult = true;
        //Act
        boolean result = washingMachine.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNonexistentFalse() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        int attribute = 3;
        double value = 20.6;
        washingMachine.setCapacity(capacity);

        boolean expectedResult = false;

        //Act
        boolean result = washingMachine.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        int attribute = 2;
        double value = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = washingMachine.setAttribute(attribute, value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        int expectedResult = 2;

        //Act
        int result = washingMachine.getNumberOfAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }
}