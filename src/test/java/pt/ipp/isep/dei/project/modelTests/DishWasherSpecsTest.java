package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DishWasherSpecsTest {
    Room kitchen;
    Device dishWasher;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        DishWasherType dishWasherType = new DishWasherType();
        this.dishWasher = dishWasherType.createDevice("DishWasher Bosch", kitchen);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Dishwasher";

        //Act
        String result = dishWasher.getType();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        dishWasher.setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = dishWasher.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        dishWasher.setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = dishWasher.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange
        dishWasher.setAttributesDevType("Nominal Power", 30);
        dishWasher.setAttributesDevType("Capacity", 30);

        String expectedResult = "1 - Capacity: 30\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = dishWasher.getSpecsToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        dishWasher.setAttributesDevType("Nominal Power", 30);
        dishWasher.setAttributesDevType("Capacity", 30);

        int expectedResult = 2;

        //Act
        int result = dishWasher.getNumberOfSpecsAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetEnergyConsumptionInADay() {
        dishWasher.setAttributesDevType("Nominal Power", 30);
        dishWasher.setAttributesDevType("Capacity", 30);

        double expectedResult = 0;

        //Act
        double result = dishWasher.getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = dishWasher.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        dishWasher.setAttributesDevType("Capacity", 30);

        Object expectedResult = 100.0;
        // Act
        Object result = dishWasher.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        dishWasher.setAttributesDevType("Capacity", 30);

        Object expectedResult = 30;
        // Act
        Object result = dishWasher.getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueDuration() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        dishWasher.setAttributesDevType("Duration", 30);
        dishWasher.setAttributesDevType("Capacity", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = dishWasher.getAttributeValue("Duration");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        dishWasher.setAttributesDevType("Capacity", 20.0);

        Object expectedResult = -1;
        // Act
        Object result = dishWasher.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.setAttributesDevType("Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.setAttributesDevType("Duration", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.setAttributesDevType("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValidValue() {
        // Arrange
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNotAValidAttribute() {
        // Arrange
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = dishWasher.setAttributesDevType("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValidValue() {
        // Arrange
        // Act
        boolean result = dishWasher.setAttributesDevType("Duration", 100.0);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeDurationSameValue() {
        // Arrange
        dishWasher.setAttributesDevType("Duration", 100.0);
        // Act
        boolean result = dishWasher.setAttributesDevType("Duration", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValueZero() {
        // Arrange
        dishWasher.setAttributesDevType("Duration", 100.0);
        // Act
        boolean result = dishWasher.setAttributesDevType("Duration", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = dishWasher.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    public void newProgram() {
        //Arrange
        String programName = "Economic";
        double duration = 0.5;
        double energyConsumption = 12.0;
        Programmable dishwasher = this.dishWasher.asProgrammable();
        Program expectedResult = new Program(programName, duration, energyConsumption);
        //Act
        Program result = dishwasher.newProgram(programName, duration, energyConsumption);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        Program program = null;
        boolean expectedResult = false;
        Programmable programmable = this.dishWasher.asProgrammable();
        //Act
        boolean result = programmable.addProgram(program);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        ProgramList programList = new ProgramList();
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.dishWasher.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);
        Program programB = programmable.newProgram(programName, duration, energyConsumption);
        programList.addProgram(programA);
        boolean expectedResult = false;

        //Act
        boolean result = programList.addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        ProgramList programList = new ProgramList();
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.dishWasher.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);

        boolean expectedResult = true;

        //Act
        boolean result = programList.addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }
}