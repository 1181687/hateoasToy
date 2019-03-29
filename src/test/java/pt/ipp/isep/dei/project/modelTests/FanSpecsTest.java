package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.fan.FanSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FanSpecsTest {
    private Room kitchen;
    private Device fan;
    private House house;
    private DeviceSpecs fanSpecs;
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";


    @Before
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
        this.fan = this.house.createDevice("Fan", "Fan200", kitchen);
        this.fan.setAttributesDevType("Nominal Power", 30);
        this.fan.setAttributesDevType("Time", 30);
        this.fanSpecs = fan.getSpecs();

    }


    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fan";

        //Act
        String result = fan.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange

        double expectedResult = 30;

        //Act
        double result = fan.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        fan.setAttributesDevType("Nominal Power", 30);

        double expectedResult = 30;

        //Act
        double result = fan.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }


    @Test
    public void testGetAttributesToString() {
        //Arrange

        String expectedResult = "1 - Nominal Power: 30.0\n";

        //Act
        String result = fan.getSpecs().getAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange

        int expectedResult = 1;

        //Act

        int result = fan.getSpecs().getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInADay() {

        double expectedResult = 0;

        //Act
        double result = fan.getSpecs().getEnergyConsumptionInADay();

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = fan.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        fan.setAttributesDevType("Nominal Power", 100.0);

        Object expectedResult = 100.0;
        // Act
        Object result = fan.getSpecs().getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;
        // Act
        Object result = fan.getSpecs().getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeTimeValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Time", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Nominal Power", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValidValue() {
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Nominal Power", 1.3);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNotAValidAttribute() {
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Wrong Attribute", 1.3);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        fan.setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValueZero() {
        // Arrange
        fan.setAttributesDevType("Nominal Power", 0);
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Nominal Power", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeTimeValueZero() {
        // Act
        boolean result = fan.getSpecs().setAttributeValue("Time", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        DeviceSpecs fanSpecs = fan.getSpecs();
        //Act
        boolean result = ((FanSpecs) fanSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.fanSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        programmable.addProgram(programA);

        DeviceSpecs fanSpecs = fan.getSpecs();

        boolean expectedResult = false;

        //Act
        boolean result = ((FanSpecs) fanSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.fanSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);

        DeviceSpecs fanSpecs = fan.getSpecs();

        boolean expectedResult = true;

        //Act
        boolean result = ((FanSpecs) fanSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.fanSpecs.asProgrammable();
        TimeVariableProgramSpecs programASpecs = new TimeVariableProgramSpecs();
        programASpecs.setTime(30.0);
        programASpecs.setProgramNominalPower(50.0);
        Program programA = programmable.createNewProgram(programName);
        DeviceSpecs fanSpecs = fan.getSpecs();
        ((FanSpecs) fanSpecs).addProgram(programA);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programA);

        //Act
        List<Program> result = ((FanSpecs) fanSpecs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsProgrammable() {
        //Act
        boolean result = fanSpecs.isProgrammable();

        //Assert
        assertTrue(result);
    }


    @Test
    public void testAsProgrammable() {
        //Arrange
        Programmable expectedResult = (Programmable) fanSpecs;

        //Act
        Programmable result = fanSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }
}