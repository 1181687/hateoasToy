package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

public class FreezerSpecsTest {
    private Room kitchen;
    private Device freezer;
    private House house;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        freezer = house.createDevice("Freezer", "Freezer Ariston", kitchen);
        this.house.addRoom(kitchen);

        // FridgeSpecs Set
        freezer.setAttributesDevType("Nominal Power", 100.0);
        freezer.setAttributesDevType("Freezer Capacity", 20.0);
        freezer.setAttributesDevType("Annual Energy Consumption", 10000.0);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Freezer";

        //Act
        String result = freezer.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        double expectedResult = 27.4;

        // Act
        double result = freezer.getSpecs().getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        double expectedResult = 100.0;

        //Act
        double result = freezer.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getAttributesToString() {

        // Arrange
        // FridgeSpecs Instantiation
        String expectedResult =
                "1 - Freezer Capacity: 20.0\n" +
                        "2 - Annual Energy Consumption: 10000.0\n" +
                        "3 - Nominal Power: 100.0\n";

        // Act
        String result = freezer.getSpecs().getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 3;

        // Act
        int result = freezer.getSpecs().getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = freezer.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        Object expectedResult = 100.0;

        // Act
        Object result = freezer.getSpecs().getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueFreezerCapacity() {
        // Arrange
        Object expectedResult = 20.0;

        // Act
        Object result = freezer.getSpecs().getAttributeValue("Freezer Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption() {
        // Arrange
        Object expectedResult = 10000.0;

        // Act
        Object result = freezer.getSpecs().getAttributeValue("Annual Energy Consumption");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = -1;

        // Act
        Object result = freezer.getSpecs().getAttributeValue("Not Valid");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueValidType() {
        // Arrange
        int capacity = 15;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Freezer Capacity", capacity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Freezer Capacity", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionValueValidType() {
        // Arrange
        double annualPowerConsumption = 20.3;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Annual Energy Consumption", annualPowerConsumption);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Annual Energy Consumption", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualNominalPowerValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Nominal Power", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        double nominalPower = 1.5;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacitySameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionSameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";

        // act
        String result = freezer.getSpecs().getAttributeDataType("Integer");

        // assert
        assertEquals(attributeDataType, result);
    }
}