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

public class DishWasherSpecsTest {
    private Room kitchen;
    private Device dishWasher;
    private House house;
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        //Room
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", 1, dim);
        this.house.addRoom(kitchen);
        this.dishWasher = this.house.createDevice("DishWasher", "dishwasher Bosch", kitchen);
        this.dishWasher.setAttributesDevType("Nominal Power", 30);
        this.dishWasher.setAttributesDevType("Capacity", 30);

    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "DishWasher";

        //Act
        String result = dishWasher.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange

        double expectedResult = 30;

        //Act
        double result = dishWasher.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Capacity: 30\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = dishWasher.getSpecs().getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 2;

        //Act

        int result = dishWasher.getSpecs().getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = dishWasher.getSpecs().getEnergyConsumptionInADay();

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
        List<String> result = dishWasher.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNullChar() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("\0Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        Object expectedResult = 30;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacityNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("\0Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }



    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeCapacityValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("\0Capacity", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("\0Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeNominalPowerValidValue() {
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNotAValidAttribute() {
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }


    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = NOT_VALID_ATTRIBUTE;
        // act
        String result = dishWasher.getSpecs().getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }

}