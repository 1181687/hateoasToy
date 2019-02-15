package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.FridgeType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    /*

    @Test
    public void setmFreezerCapacityFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;

        //act
        boolean result = livingRoom.getDeviceByPosition(0).setFreezerCapacity(20.0);

        assertFalse(result);
    }

    @Test
    public void setmRefrigeratorCapacityTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        
        //act
        boolean result = livingRoom.getDeviceByPosition(0).setRefrigeratorCapacity(20.0);

        assertTrue(result);
    }

    @Test
    public void setmRefrigeratorCapacityFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;

        //act
        boolean result = livingRoom.getDeviceByPosition(0).setRefrigeratorCapacity(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        
        //act
        boolean result = livingRoom.getDeviceByPosition(0).setNominalPower(100.0);

        assertFalse(result);
    }

    @Test
    public void setmNominalPowerTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        
        //act
        boolean result = livingRoom.getDeviceByPosition(0).setNominalPower(20.0);

        assertTrue(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionFalse() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        
        //act
        boolean result = livingRoom.getDeviceByPosition(0).setAnnualEnergyConsumption(10000.0);

        assertFalse(result);
    }

    @Test
    public void setmAnnualEnergyConsumptionTrue() {
        // Arrange
        // FridgeSpecs Instantiation
        double freezerCapacity = 20.0;
        double refrigeratorCapacity = 100.0;
        double annualEnergyConsumption = 10000.0;
        double nominalPower = 100.0;
        
        //act
        boolean result = livingRoom.getDeviceByPosition(0).setAnnualEnergyConsumption(2000.0);

        assertTrue(result);
    }*/


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
    public void testgetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Refrigerator Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = kitchen.getDeviceByPosition(0).getSpecs().getSpecsList();

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
        Object result = kitchen.getDeviceByPosition(0).getSpecs().getAttributeValue("Nominal Power");
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
        Object result = kitchen.getDeviceByPosition(0).getSpecs().getAttributeValue("Freezer Capacity");
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
        Object result = kitchen.getDeviceByPosition(0).getSpecs().getAttributeValue("Refrigerator Capacity");
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
        Object result = kitchen.getDeviceByPosition(0).getSpecs().getAttributeValue("Annual Energy Consumption");
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
        Object result = kitchen.getDeviceByPosition(0).getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }
}
