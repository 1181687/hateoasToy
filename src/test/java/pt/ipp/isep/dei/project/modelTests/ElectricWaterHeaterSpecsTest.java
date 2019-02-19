package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ElectricWaterHeaterSpecsTest {
    private Room kitchen;
    private Device electricWaterHeater;

    @BeforeEach
    public void StartUp() {
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        electricWaterHeater = electricWaterHeaterType.createDevice("DishWasher Bosch", kitchen);
    }

    @Test
    public void getEnergyConsumptionInADayTest1() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 100);
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 50);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 2.0934;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTestCoiso() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 30);
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 80);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 5.2335;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getEnergyConsumptionInADayTest2() {
        // Arrange
        // ElectricWaterHeaterSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 30);
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 70);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        double expectedResult = 4.1868;

        // Act
        double result = electricWaterHeater.getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.000001);
    }

    @Test
    public void getAttributesToString() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Nominal Power", 30);
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 50);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        String expectedResult = "1 - Hot Water Temperature: 50.0\n" +
                "2 - Performance Ratio: 0.9\n" +
                "3 - Nominal Power: 30.0\n";
        // Act
        String result = electricWaterHeater.getSpecsToString();
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Hot-Water Temperature");
        expectedResult.add("Performance Ratio");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = electricWaterHeater.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueVolumeOfWaterToHeat() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);

        Object expectedResult = 100.0;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Volume Of Water To Heat");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValuePerformanceRatio() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);

        Object expectedResult = 0.9;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Performance Ratio");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueHotWaterTemperature() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 50);

        Object expectedResult = 50.0;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Hot-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueColdWaterTemperature() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 80);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Cold-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 20.0);

        Object expectedResult = -1;
        // Act
        Object result = electricWaterHeater.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Wrong Attribute", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeHotWaterTemperatureValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Hot-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeColdWaterTemperatureTrue() {
        //Arrange
        double value1 = 10;
        String attribute1 = "Cold-Water Temperature";
        electricWaterHeater.setAttributesDevType(attribute1, value1);
        String attribute2 = "Hot-Water Temperature";
        double value2 = 30;
        electricWaterHeater.setAttributesDevType(attribute2, value2);

        boolean expectedResult = true;

        //Act
        boolean result = electricWaterHeater.setAttributesDevType(attribute1, value1);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeColdWaterTemperatureFalse() {
        //Arrange
        double value1 = 30;
        String attribute1 = "Cold-Water Temperature";
        electricWaterHeater.setAttributesDevType(attribute1, value1);
        String attribute2 = "Hot-Water Temperature";
        double value2 = 20;
        electricWaterHeater.setAttributesDevType(attribute2, value2);

        boolean expectedResult = false;

        //Act
        boolean result = electricWaterHeater.setAttributesDevType(attribute1, value1);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = kitchen.getDeviceByPosition(0).setAttributesDevType("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributePerformanceRatioValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Performance Ratio", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeColdWaterTemperatureValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Cold-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidTypeNegative() {
        // Arrange
        double value = -200;
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", value);
        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeVolumeOfWaterToHeatFalseTest() {
       //Arrange
        double value = 0;
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", value);
        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeVolumeOfWaterToHeatTrueTest() {
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        double value1 = 30.5;
        String attribute = "Nominal Power";
        electricWaterHeater.setAttributesDevType(attribute, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = electricWaterHeater.setAttributesDevType(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributePerformanceRatioSameValue() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        // Act
        boolean result = electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributePerformanceRatioTrue() {
        //Arrange
        double value1 = 0.8;
        String attribute = "Performance Ratio";
        electricWaterHeater.setAttributesDevType(attribute, value1);
        double value2 = 0.9;

        boolean expectedResult = true;

        //Act
        boolean result = electricWaterHeater.setAttributesDevType(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = electricWaterHeater.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }
}