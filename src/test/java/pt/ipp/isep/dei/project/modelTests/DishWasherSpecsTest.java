package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.dishwasher.DishWasherSpecs;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DishWasherSpecsTest {
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";
    private Room kitchen;
    private Device dishWasher;
    private House house;
    private DeviceSpecs dishWasherSpecs;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        Address address = new Address(null, null, null);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        //Room
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", "room", 1, dim);
        this.house.addRoom(kitchen);
        this.dishWasher = this.house.createDevice("DishWasher", "dishwasher Bosch", kitchen);
        this.dishWasher.setAttributesDevType("Nominal Power", 30);
        this.dishWasher.setAttributesDevType("Capacity", 30);
        this.dishWasherSpecs = dishWasher.getSpecs();

    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "DishWasher";

        //Act
        String result = dishWasherSpecs.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange

        double expectedResult = 30;

        //Act
        double result = dishWasherSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Capacity: 30\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = dishWasherSpecs.getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 2;

        //Act

        int result = dishWasherSpecs.getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testgetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = dishWasherSpecs.getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testgetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = dishWasherSpecs.getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = dishWasherSpecs.getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNullChar() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasherSpecs.getAttributeValue("\0Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        Object expectedResult = 30;
        // Act
        Object result = dishWasherSpecs.getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacityNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasherSpecs.getAttributeValue("\0Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = dishWasherSpecs.getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeCapacityValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("\0Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetCapacity_False() {
        boolean expectedResult = false;
        dishWasherSpecs.setAttributeValue("capacity", 0);

        boolean result = dishWasherSpecs.setAttributeValue("capacity", 0);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNullChar() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("\0Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeNominalPowerValidValue() {
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNotAValidAttribute() {
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        dishWasher.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = dishWasherSpecs.setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetNominalPower_False() {
        boolean expectedResult = false;
        dishWasherSpecs.setAttributeValue("nominalPower", 0);

        boolean result = dishWasherSpecs.setAttributeValue("nominalPower", 0);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = NOT_VALID_ATTRIBUTE;
        // act
        String result = dishWasherSpecs.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        DeviceSpecs dWSpecs = dishWasher.getSpecs();
        //Act
        boolean result = ((DishWasherSpecs) dWSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.dishWasherSpecs.asProgrammable();
        TimeConstantProgramSpecs programSpecs = new TimeConstantProgramSpecs();
        programSpecs.setDuration(50.0);
        programSpecs.setEnergyConsumption(20.0);
        Program program = programmable.createNewProgram(programName);
        programmable.addProgram(program);

        DeviceSpecs dWSpecs = dishWasher.getSpecs();

        boolean expectedResult = false;

        //Act
        boolean result = ((DishWasherSpecs) dWSpecs).addProgram(program);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.dishWasherSpecs.asProgrammable();
        TimeConstantProgramSpecs programBSpecs = new TimeConstantProgramSpecs();
        programBSpecs.setDuration(30.0);
        programBSpecs.setEnergyConsumption(50.0);
        Program programB = programmable.createNewProgram(programName);

        DeviceSpecs dWSpecs = dishWasher.getSpecs();

        boolean expectedResult = true;

        //Act
        boolean result = ((DishWasherSpecs) dWSpecs).addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.dishWasherSpecs.asProgrammable();
        TimeConstantProgramSpecs programCSpecs = new TimeConstantProgramSpecs();
        programCSpecs.setDuration(30.0);
        programCSpecs.setEnergyConsumption(50.0);
        Program programC = programmable.createNewProgram(programName);
        DeviceSpecs dWSpecs = dishWasher.getSpecs();
        ((DishWasherSpecs) dWSpecs).addProgram(programC);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programC);

        //Act
        List<Program> result = ((DishWasherSpecs) dWSpecs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsProgrammable() {
        //Act
        boolean result = dishWasherSpecs.isProgrammable();

        //Assert
        assertTrue(result);
    }


    @Test
    public void testAsProgrammable() {
        //Arrange
        Programmable expectedResult = (Programmable) dishWasherSpecs;

        //Act
        Programmable result = dishWasherSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAsProgrammable_device() {
        Programmable expectedResult = (Programmable) dishWasherSpecs;
        Programmable result = dishWasherSpecs.asProgrammable();
        assertEquals(expectedResult, result);

    }

}