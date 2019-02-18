package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.FridgeType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FridgeSpecsTest {
    Room kitchen;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        FridgeType fridgeType = new FridgeType();
        fridgeType.createDevice("Fridge Ariston", kitchen);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fridge";

        //Act
        String result = kitchen.getDeviceByPosition(0).getType();

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
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

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
        double result = kitchen.getDeviceByPosition(0).getNominalPower();

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
        String result = kitchen.getDeviceByPosition(0).getDevSpecsAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 4;

        // Act
        int result = kitchen.getDeviceByPosition(0).getNumberOfSpecsAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("1 - Freezer Capacity: 0.0");
        expectedResult.add("2 - Refrigerator Capacity: 0.0");
        expectedResult.add("3 - Annual Energy Consumption: 0.0");
        expectedResult.add("4 - Nominal Power: 0.0");

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
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Energy Consumption", 10000.0);

        Object expectedResult = 100.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Nominal Power");
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
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Freezer Capacity");
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
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Refrigerator Capacity");
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
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Annual Energy Consumption");
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
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Annual Power Consumption", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 1.5);
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacitySameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20);
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Freezer Capacity", 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacitySameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 50);
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Refrigerator Capacity", 50);
        // Assert
        assertFalse(result);
    }
}
