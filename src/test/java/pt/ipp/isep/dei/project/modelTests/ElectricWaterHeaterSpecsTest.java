package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ElectricWaterHeaterSpecsTest {
    private House house;
    private Room kitchen;
    private Device electricWaterHeater;
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";


    @BeforeEach
    public void StartUp() {

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);

        electricWaterHeater = house.createDevice(ELECTRIC_W_H_TYPE, "dishwasher Bosch", kitchen);
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
        double result = electricWaterHeater.getSpecs().getEnergyConsumptionInADay();

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
        double result = electricWaterHeater.getSpecs().getEnergyConsumptionInADay();

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
        double result = electricWaterHeater.getSpecs().getEnergyConsumptionInADay();

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
        String result = electricWaterHeater.getSpecs().getAttributesToString();
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
        List<String> result = electricWaterHeater.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPowerNullChar() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("\0Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueVolumeOfWaterToHeat() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);

        Object expectedResult = 100.0;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Volume Of Water To Heat");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueVolumeOfWaterToHeatNullChar() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Volume Of Water To Heat", 100);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("\0Volume Of Water To Heat");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValuePerformanceRatio() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);

        Object expectedResult = 0.9;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Performance Ratio");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValuePerformanceRatioNullChar() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("\0Performance Ratio");
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
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Hot-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributeValueHotWaterTemperatureNullChar() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 50);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("\0Hot-Water Temperature");
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
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Cold-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueColdWaterTemperatureNullChar() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 80);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 30);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("\0Cold-Water Temperature");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);
        electricWaterHeater.setAttributesDevType("Cold-Water Temperature", 20.0);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = electricWaterHeater.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Wrong Attribute", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Volume Of Water To Heat", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNullCharacter() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("\0Volume Of Water To Heat", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeHotWaterTemperatureValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Hot-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeHotWaterTemperatureValueNullCharacter() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("\0Hot-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributePerformanceRatioValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Performance Ratio", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributePerformanceRatioValueNullCharacter() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("\0Performance Ratio", attribute);
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
        boolean result = electricWaterHeater.getSpecs().setAttributeValue(attribute1, value1);

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
        boolean result = electricWaterHeater.getSpecs().setAttributeValue(attribute1, value1);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeColdWaterTemperatureValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Cold-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeColdWaterTemperatureValueNullCharacter() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("\0Cold-Water Temperature", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeVolumeOfWaterToHeatValueNotAValidTypeNegative() {
        // Arrange
        double value = -200;
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Volume Of Water To Heat", value);
        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeVolumeOfWaterToHeatFalseTest() {
       //Arrange
        double value = 0;
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Volume Of Water To Heat", value);
        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeVolumeOfWaterToHeatTrueTest() {
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Volume Of Water To Heat", 1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerNullCharacter() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("\0Nominal Power", attribute);
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
        boolean result = electricWaterHeater.getSpecs().setAttributeValue(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributePerformanceRatioSameValue() {
        // Arrange
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        // Act
        boolean result = electricWaterHeater.getSpecs().setAttributeValue("Performance Ratio", 0.9);
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
        boolean result = electricWaterHeater.getSpecs().setAttributeValue(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = NOT_VALID_ATTRIBUTE;
        // act
        String result = electricWaterHeater.getSpecs().getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }
}