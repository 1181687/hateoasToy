package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.FridgeType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

public class FridgeSpecsTest {
    Room kitchen;
    Device fridge;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        FridgeType fridgeType = new FridgeType();
        fridge = fridgeType.createDevice("Fridge Ariston", kitchen);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fridge";

        //Act
        String result = fridge.getType();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);
        
        double expectedResult = 27.3972;

        // Act
        double result = fridge.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);

        double expectedResult = 100.0;

        //Act
        double result = fridge.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getAttributesToString() {

        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);
        
        String expectedResult =
                "1 - Freezer Capacity: 20.0\n" +
                        "2 - Refrigerator Capacity: 100.0\n" +
                        "3 - Annual Energy Consumption: 10000.0\n" +
                        "4 - Nominal Power: 100.0\n";

        // Act
        String result = fridge.getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 4;

        // Act
        int result = fridge.getNumberOfSpecsAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Refrigerator Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = fridge.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = 100.0;
        // Act
        Object result = fridge.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueFreezerCapacity() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = 20.0;
        // Act
        Object result = fridge.getAttributeValue("Freezer Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueRefrigeratorCapacity() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = 100.0;
        // Act
        Object result = fridge.getAttributeValue("Refrigerator Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = 10000.0;
        // Act
        Object result = fridge.getAttributeValue("Annual Energy Consumption");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = -1;
        // Act
        Object result = fridge.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueValidType() {
        // Arrange
        int capacity = 15;
        // Act
        boolean result = fridge.setAttributesDevType("Freezer Capacity", capacity);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueNotAValidType() {
        // Arrange
        String stuff = "stuff";
        // Act
        boolean result = fridge.setAttributesDevType("Freezer Capacity", stuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueValidType() {
        // Arrange
        double refrigeratorCapacity = 20;
        // Act
        boolean result = fridge.setAttributesDevType("Refrigerator Capacity", refrigeratorCapacity);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueNotAValidType() {
        // Arrange
        String stuff = "stuff";
        // Act
        boolean result = fridge.setAttributesDevType("Refrigerator Capacity", stuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionValueValidType() {
        // Arrange
        double annualPowerConsumption = 20.3;
        // Act
        boolean result = fridge.setAttributesDevType("Annual Energy Consumption", annualPowerConsumption);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValueNotAValidType() {
        // Arrange
        String stuff = "stuff";
        // Act
        boolean result = fridge.setAttributesDevType("Annual Energy Consumption", stuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        double nominalPower = 1.5;
        // Act
        boolean result = fridge.setAttributesDevType("Nominal Power", nominalPower);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        fridge.setAttributesDevType("Nominal Power", 1.5);
        // Act
        boolean result = fridge.setAttributesDevType("Nominal Power", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacitySameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20);
        // Act
        boolean result = fridge.setAttributesDevType("Freezer Capacity", 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacitySameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 50);
        // Act
        boolean result = fridge.setAttributesDevType("Refrigerator Capacity", 50);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = fridge.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }
}
