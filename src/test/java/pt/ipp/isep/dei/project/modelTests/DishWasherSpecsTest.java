package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        String result = kitchen.getDeviceByPosition(0).getType();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = kitchen.getDeviceByPosition(0).getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = kitchen.getDeviceByPosition(0).getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        String expectedResult = "1 - Capacity: 30\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = kitchen.getDeviceByPosition(0).getSpecsToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        int expectedResult = 2;

        //Act
        int result = kitchen.getDeviceByPosition(0).getNumberOfSpecsAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetEnergyConsumptionInADay() {
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        double expectedResult = 0;

        //Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

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
        List<String> result = kitchen.getDeviceByPosition(0).getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 100.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 30;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueDuration() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Duration", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Duration");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", 20.0);

        Object expectedResult = -1;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Capacity", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValueNotAValidType() {
        // Arrange
        String stuff = "stuff";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Duration", stuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String stuff = "stuff";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", stuff);
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
}