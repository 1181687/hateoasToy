package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ElectricWaterHeaterSpecsTest {
    Room kitchen;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        electricWaterHeaterType.createDevice("DishWasher Bosch", kitchen);
    }

    @Test
    public void getEnergyConsumptionInADayTest1() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 50);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 2.09;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTestCoiso() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 80);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 5.23;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTest2() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 70);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 4.19;

        // Act
        double result = kitchen.getDeviceByPosition(0).getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getAttributesToString() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 30);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 50);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Performance Ratio: 0.9\n" +
                "3 - Nominal Power: 30.0\n";
        // Act
        String result = kitchen.getDeviceByPosition(0).getSpecsToString();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("1 - Hot-Water Temperature: 0.0");
        expectedResult.add("2 - Performance Ratio: 0.0");
        expectedResult.add("3 - Nominal Power: 0.0");

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

        Object expectedResult = 100.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueVolumeOfWaterToHeat() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", 100);

        Object expectedResult = 100.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Volume Of Water To Heat");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValuePerformanceRatio() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);

        Object expectedResult = 0.9;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Performance Ratio");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueHotWaterTemperature() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 50);


        Object expectedResult = 50.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Hot-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueColdWaterTemperature() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", 80);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Cold-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", 20.0);

        Object expectedResult = -1;
        // Act
        Object result = kitchen.getDeviceByPosition(0).getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeHotWaterTemperatureValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Hot-Water Temperature", tuff);
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
    public void testSetAttributePerformanceRatioValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeColdWaterTemperatureValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Cold-Water Temperature", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidTypeNegative() {
        // Arrange
        double tuff = -200;
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Volume Of Water To Heat", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributePerformanceRatioSameValue() {
        // Arrange
        kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Performance Ratio", 0.9);
        // Assert
        assertFalse(result);
    }



}