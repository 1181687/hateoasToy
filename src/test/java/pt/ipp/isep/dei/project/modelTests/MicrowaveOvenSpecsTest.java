package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.microwaveoven.MicrowaveOvenSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MicrowaveOvenSpecsTest {
    private Room kitchen;
    private Device microwaveOven;
    private House house;
    private DeviceSpecs microwaveOvenSpecs;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        //Room
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", "room", 1, dim);
        this.house.addRoom(kitchen);
        this.microwaveOven = this.house.createDevice("MicrowaveOven", "MicrowaveOven teka", kitchen);
        this.microwaveOven.setAttributesDevType("Nominal Power", 30);
        this.microwaveOvenSpecs = microwaveOven.getSpecs();

    }


    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "MicrowaveOven";

        //Act
        String result = microwaveOven.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange

        double expectedResult = 30;

        //Act
        double result = microwaveOven.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @org.junit.jupiter.api.Test
    public void testEmptyConstructor() {
        //Arrange
        microwaveOven.setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = microwaveOven.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Nominal Power: 30.0\n";

        //Act
        String result = microwaveOven.getSpecs().getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 1;

        //Act

        int result = microwaveOven.getSpecs().getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = microwaveOven.getSpecs().getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @org.junit.jupiter.api.Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = microwaveOven.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        microwaveOven.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = microwaveOven.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAttributeValue_notAValidSpec() {
        // Arrange
        Object expectedResult = "not a valid attribute";
        // Act
        Object result = microwaveOven.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeNominalPowerValue_NotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = microwaveOven.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttributeNominalPower_ValidValue() {
        // Act
        boolean result = microwaveOven.getSpecs().setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    public void testSetAttribute_NotAValidAttribute() {
        // Act
        boolean result = microwaveOven.getSpecs().setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPower_SameValue() {
        // Arrange
        microwaveOven.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = microwaveOven.getSpecs().setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPower_SameValueZero() {
        // Arrange
        microwaveOven.setAttributesDevType("Nominal Power", 0);
        // Act
        boolean result = microwaveOven.getSpecs().setAttributeValue("Nominal Power", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest_notValidAttributte() {
        // arrange
        String expectedResult = "not a valid attribute";
        // act
        String result = microwaveOven.getSpecs().getAttributeDataType("wrong attribute name");
        // assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void getAttributeDataTypeTest() {
        // arrange
        String expectedResult = "Double";
        // act
        String result = microwaveOven.getSpecs().getAttributeDataType("Nominal Power");
        // assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        DeviceSpecs microwaveOvenSpecs = microwaveOven.getSpecs();
        //Act
        boolean result = ((MicrowaveOvenSpecs) microwaveOvenSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.microwaveOvenSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        programmable.addProgram(programA);

        DeviceSpecs microwaveOvenSpecs = microwaveOven.getSpecs();

        boolean expectedResult = false;

        //Act
        boolean result = ((MicrowaveOvenSpecs) microwaveOvenSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.microwaveOvenSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);

        DeviceSpecs microwaveOvenSpecs = microwaveOven.getSpecs();

        boolean expectedResult = true;

        //Act
        boolean result = ((MicrowaveOvenSpecs) microwaveOvenSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.microwaveOvenSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        DeviceSpecs microwaveOvenSpecs = microwaveOven.getSpecs();
        ((MicrowaveOvenSpecs) microwaveOvenSpecs).addProgram(programA);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programA);

        //Act
        List<Program> result = ((MicrowaveOvenSpecs) microwaveOvenSpecs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsProgrammable() {
        //Act
        boolean result = microwaveOvenSpecs.isProgrammable();

        //Assert
        assertTrue(result);
    }


    @Test
    public void testAsProgrammable() {
        //Arrange
        Programmable expectedResult = (Programmable) microwaveOvenSpecs;

        //Act
        Programmable result = microwaveOvenSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

}