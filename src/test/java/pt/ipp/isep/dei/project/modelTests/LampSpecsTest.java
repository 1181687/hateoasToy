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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LampSpecsTest {
    private Room livingRoom;
    private Device lamp;
    private House house;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        //Room
        Dimension dim = new Dimension(3, 5, 6);
        livingRoom = new Room("Living Room", 1, dim);
        this.house.addRoom(livingRoom);

        //Device
        this.lamp = this.house.createDevice("Lamp", "lamp Philips", livingRoom);
        lamp.setAttributesDevType("Luminous Flux", 50.0);
        lamp.setAttributesDevType("Nominal Power", 100.0);
        lamp.setAttributesDevType("Time", 10.0);
    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {

        // Arrange
        double expectedResult = 1000.0;

        // Act
        double result = lamp.getSpecs().getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange

        double expectedResult = 100.0;

        //Act
        double result = lamp.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void testEmptyConstructor() {
        // Arrange

        double expectedResult = 100.0;

        //Act
        double result = lamp.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getAttributesToString() {
        // Arrange

        String expectedResult =
                "1 - Luminous Flux: 50.0\n" +
                        "2 - Nominal Power: 100.0\n";

        // Act
        String result = lamp.getSpecs().getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange

        int expectedResult = 2;

        // Act
        int result = lamp.getSpecs().getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Luminous Flux");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = lamp.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange

        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = lamp.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueLuminousFlux() {
        // Arrange
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getSpecs().getAttributeValue("Luminous Flux");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = -1;
        // Act
        Object result = lamp.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeTimeValueNotAValidSpec() {
        // Arrange
        lamp.setAttributesDevType("Time", 50);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getSpecs().getAttributeValue("Time");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeLuminousFlux() {
        // Arrange
        lamp.setAttributesDevType("Nominal Power", 30);

        Object expectedResult = 50.0;
        // Act
        Object result = lamp.getSpecs().getAttributeValue("Luminous Flux");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeLuminousFluxNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Luminous Flux", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeLuminousFluxSameValue() {
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Luminous Flux", 50);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Time", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeTrue() {
        //Arrange

        double value1 = 30.5;
        String attribute = "Time";
        lamp.setAttributesDevType(attribute, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = lamp.getSpecs().setAttributeValue(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSetAttributeTimeSameValue() {
        // Arrange
        lamp.setAttributesDevType("Time", 20);
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Time", 20);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        lamp.setAttributesDevType("Nominal Power", 1.5);
        // Act
        boolean result = lamp.getSpecs().setAttributeValue("Nominal Power", 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        double value1 = 30.5;
        String attribute = "Nominal Power";
        lamp.setAttributesDevType(attribute, value1);
        double value2 = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = lamp.getSpecs().setAttributeValue(attribute, value2);

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNotValid() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = lamp.getSpecs().setAttributeValue(attribute, 1.5);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = lamp.getSpecs().getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }
}
