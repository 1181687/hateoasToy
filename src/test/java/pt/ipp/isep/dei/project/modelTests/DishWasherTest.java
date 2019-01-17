package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.DishWasher;
import pt.ipp.isep.dei.project.model.Program;
import pt.ipp.isep.dei.project.model.ProgramList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishWasherTest {

    @Test
    public void testgetAttributeNames() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Duration");
        expectedResult.add("Energy consumption of the program");
        expectedResult.add("Nominal power");
        expectedResult.add("List of programs");

        //Act
        List<String> result = dishWasher.getAttributeNames();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        //Arrange
        int capacity = 20;
        int capacity2 = 30;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Capacity";
        Object obj = new Integer(capacity2);
        dishWasher.setAttributeValue(attributeName, obj);
        int expectedResult = 30;

        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueDuration() {
        //Arrange
        int capacity = 20;
        double duration = 30;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Duration";
        Object obj = new Double(duration);
        dishWasher.setAttributeValue(attributeName, obj);
        double expectedResult = 30;

        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributeValueEnergyConsumptionOfTheProgram() {
        //Arrange
        int capacity = 20;
        double energyConsumption = 40;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Energy consumption of the program";
        Object obj = new Double(energyConsumption);
        dishWasher.setAttributeValue(attributeName, obj);
        double expectedResult = 40;

        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        //Arrange
        int capacity = 20;
        double nominalPower2 = 10;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Nominal power";
        Object obj = new Double(nominalPower2);
        dishWasher.setAttributeValue(attributeName, obj);
        double expectedResult = 10;

        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueListOfPrograms() {
        //Arrange
        //Arrange
        int capacity = 20;
        double duration = 30;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        //program1
        String programName1 = "program1";
        double energyConsumption1 = 5;
        double duration1 = 15;
        Program program1 = new Program(programName1, duration1, energyConsumption1);

        //program2
        String programName2 = "program2";
        double energyConsumption2 = 4;
        double duration2 = 10;
        Program program2 = new Program(programName1, duration1, energyConsumption1);

        ProgramList expectedResult = new ProgramList();

        expectedResult.addProgram(program1);
        expectedResult.addProgram(program2);

        DishWasher dishWasher = new DishWasher(capacity, nominalPower, expectedResult);
        String attributeName = "List of programs";
        Object obj = new Double(duration);
        dishWasher.setAttributeValue(attributeName, obj);


        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeNonexistent() {
        //Arrange
        int capacity = 20;
        int nominalPower2 = 10;

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "NonExistent";
        Object obj = new Integer(nominalPower2);
        dishWasher.setAttributeValue(attributeName, obj);
        int expectedResult = 0;

        //Act
        Object result = dishWasher.getAttributeValue(attributeName);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseCapacity() {
        //Arrange
        int capacity = 20;
        String capacity2 = "30";

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Capacity";
        Object obj = new Double(capacity2);

        boolean expectedResult = false;

        //Act
        Object result = dishWasher.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseDuration() {
        //Arrange
        int capacity = 20;
        String duration2 = "30";

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Duration";
        Object obj = new Integer(duration2);

        boolean expectedResult = false;

        //Act
        Object result = dishWasher.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseEnergyConsumption() {
        //Arrange
        int capacity = 20;
        String energyConsumption = "30";

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Energy consumption of the program";
        Object obj = new Integer(energyConsumption);

        boolean expectedResult = false;

        //Act
        Object result = dishWasher.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeValueFalseNominalPower() {
        //Arrange
        int capacity = 20;
        String nominalPower2 = "30";

        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        String attributeName = "Nominal power";
        Object obj = new Integer(nominalPower2);

        boolean expectedResult = false;

        //Act
        Object result = dishWasher.setAttributeValue(attributeName, obj);
        //Assert
        assertEquals(expectedResult, result);
    }

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
    public void testGetEnergyConsumptionInADay() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        ProgramList programList = new ProgramList();
        DishWasher dishWasher = new DishWasher(capacity, nominalPower, programList);
        double energyConsumption = 40;
        String attributeName = "Energy consumption of the program";
        Object obj = new Double(energyConsumption);
        dishWasher.setAttributeValue(attributeName, obj);

        double expectedResult = 40;

        //Act
        double result = dishWasher.getEnergyConsumptionInADay();

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