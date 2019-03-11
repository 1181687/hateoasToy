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


public class WineCoolerSpecsTest {
    private Room kitchen;
    private Device wineCooler;
    private House house;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Dimension dim = new Dimension(2, 2, 3);
        kitchen = new Room("Kitchen", 1, dim);
        wineCooler = house.createDevice("WineCooler", "Awesome Wine Cooler", kitchen);
        this.house.addRoom(kitchen);

        // FridgeSpecs Set
        wineCooler.setAttributesDevType("Nominal Power", 100.0);
        wineCooler.setAttributesDevType("Number of Bottles", 33);
        wineCooler.setAttributesDevType("Annual Energy Consumption", 10000.0);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "WineCooler";

        //Act
        String result = wineCooler.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        double expectedResult = 100.0;

        //Act
        double result = wineCooler.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        //10000/365
        double expectedResult = 27.4;

        // Act
        double result = wineCooler.getSpecs().getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }


    @Test
    public void getAttributesToString() {

        // Arrange
        // WineCoolerSpecs Instantiation
        String expectedResult =
                "1 - Nominal Power: 100.0\n" +
                        "2 - Number of Bottles: 33\n" +
                        "3 - Annual Energy Consumption: 10000.0\n";

        // Act
        String result = wineCooler.getSpecs().getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = wineCooler.getSpecs().getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");
        expectedResult.add("Number of Bottles");
        expectedResult.add("Annual Energy Consumption");

        // Act
        List<String> result = wineCooler.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        Object expectedResult = 100.0;

        // Act
        Object result = wineCooler.getSpecs().getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNumberOfBottles() {
        // Arrange
        Object expectedResult = 33;

        // Act
        Object result = wineCooler.getSpecs().getAttributeValue("Number of Bottles");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption() {
        // Arrange
        Object expectedResult = 10000.0;

        // Act
        Object result = wineCooler.getSpecs().getAttributeValue("Annual Energy Consumption");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = -1;

        // Act
        Object result = wineCooler.getSpecs().getAttributeValue("Not Valid");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNumberOfBottlesValueValidType() {
        // Arrange
        int number = 30;

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Number of Bottles", number);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNumberOfBottlesValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Number of Bottles", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNumberOfBottlesSameValue() {
        // Arrange
        wineCooler.getSpecs().setAttributeValue("Number of Bottles", 20);

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Number of Bottles", 20);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionValueValidType() {
        // Arrange
        double annualPowerConsumption = 20.4;

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Annual Energy Consumption", annualPowerConsumption);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Annual Energy Consumption", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionSameValue() {
        // Arrange
        wineCooler.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String random = "random";

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Nominal Power", random);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueValidType() {
        // Arrange
        double nominalPower = 1.5;

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        wineCooler.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Act
        boolean result = wineCooler.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";

        // act
        String result = wineCooler.getSpecs().getAttributeDataType("Integer");

        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    void testIfDeviceIsProgrammableFalse() {
        //Arrange
        //Act
        boolean result = wineCooler.getSpecs().isProgrammable();
        //Assert
        assertFalse(result);
    }

    @Test
    void testIfDeviceIsProgrammableReturnsFalseBecauseItsNotProgrammable() {
        //Arrange
        wineCooler.getSpecs().asProgrammable();
        //Act
        boolean result = wineCooler.getSpecs().isProgrammable();
        //Assert
        assertFalse(result);
    }
}
