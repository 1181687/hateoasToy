package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.washingmachine.WashingMachineSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineSpecsTest {
    private Room kitchen;
    private Device washingMachine;
    private House house;
    private DeviceSpecs washingMachineSpecs;

    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_ENERGY_CONSUMPTION = "Energy Consumption";

    private static final String WASHING_MACHINE_TYPE = "WashingMachine";
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";


    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Room
        Dimension dimension = new Dimension(2, 2, 2);
        this.kitchen = new Room("Kitchen", 0, dimension);
        house.addRoom(kitchen);

        // Device
        this.washingMachine = house.createDevice(WASHING_MACHINE_TYPE, "Wm1", kitchen);
        this.washingMachine.setAttributesDevType(ATTRIBUTE_CAPACITY, 3);
        this.washingMachine.setAttributesDevType(ATTRIBUTE_ENERGY_CONSUMPTION, 200);
        //DeviceSpecs
        this.washingMachineSpecs = washingMachine.getSpecs();
    }


    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "WashingMachine";

        //Act
        String result = washingMachineSpecs.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetNominalPower() {
        //Arrange
        double expectedResult = 30;

        //Act
        double result = washingMachineSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Capacity: 3.0\n" +
                "2 - Nominal Power: 200.0\n";

        //Act
        String result = washingMachineSpecs.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 2;

        //Act

        int result = washingMachineSpecs.getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = washingMachineSpecs.getEnergyConsumptionInADay();

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
        List<String> result = washingMachineSpecs.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        washingMachine.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = washingMachineSpecs.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNullChar() {
        // Arrange
        washingMachine.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = washingMachineSpecs.getAttributeValue("\0Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        Object expectedResult = 30;
        // Act
        Object result = washingMachineSpecs.getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacityNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = washingMachineSpecs.getAttributeValue("\0Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }



    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = washingMachineSpecs.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeCapacityValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("\0Capacity", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("\0Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeNominalPowerValidValue() {
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNotAValidAttribute() {
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        washingMachine.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }


    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = NOT_VALID_ATTRIBUTE;
        // act
        String result = washingMachineSpecs.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }
}