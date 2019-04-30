package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.*;
import pt.ipp.isep.dei.project.model.devices.washingmachine.WashingMachineSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WashingMachineSpecsTest {
    private static final String ATTRIBUTE_CAPACITY = "Capacity";
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String WASHING_MACHINE_TYPE = "WashingMachine";
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";
    private Room kitchen;
    private Device washingMachine;
    private House house;
    private DeviceSpecs washingMachineSpecs;
    private Programmable washingMachineProgrammable;
    private List<Program> programList;

    @BeforeEach
    public void StartUp() {
        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        // Room
        Dimension dimension = new Dimension(2, 2, 2);
        this.kitchen = new Room("Kitchen", "room", 0, dimension);
        house.addRoom(kitchen);

        // Device
        this.washingMachine = house.createDevice(WASHING_MACHINE_TYPE, "Wm1", kitchen);
        this.washingMachine.setAttributesDevType(ATTRIBUTE_CAPACITY, 3);
        this.washingMachine.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 200);
        //DeviceSpecs
        this.washingMachineSpecs = washingMachine.getSpecs();
        washingMachineProgrammable = new WashingMachineSpecs();

        TimeConstantProgramSpecs specs = new TimeConstantProgramSpecs();
        TimeConstantProgram prog1 = new TimeConstantProgram("aaa", specs);
        programList = new ArrayList<>();
        programList.add(prog1);
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
        double expectedResult = 200;

        //Act
        double result = washingMachineSpecs.getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.001);
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
        assertEquals(expectedResult, result, 0.001);
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
        Object expectedResult = 3.0;
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
        double attribute = 250;
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("\0Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeCapacitySameValue() {
        // Arrange
        double attribute = 3;
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Capacity", attribute);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeCapacitySameValueZero() {
        // Arrange
        double attribute = 0;
        washingMachineSpecs.setAttributeValue("Capacity", attribute);
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Capacity", attribute);
        // Assert
        assertFalse(result);
    }


    @Test
    public void testSetAttributeCapacityValidValue() {
        // Act
        boolean result = washingMachineSpecs.setAttributeValue("Capacity", 1.3);
        // Assert
        assertTrue(result);
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
        String expectedResult = NOT_VALID_ATTRIBUTE;
        // act
        String result = washingMachineSpecs.getAttributeDataType("Integer");
        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsProgrammable() {
        //arrange
        boolean expectedResult = true;

        //act
        boolean result = washingMachineSpecs.isProgrammable();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetNominalPowerZeroSameValue_False() {
        boolean expectedResult = false;
        washingMachineSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, 0);

        boolean result = washingMachineSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, 0);
        //Assert
        assertEquals(expectedResult, result);


    }

    @Test
    public void testSetCapacityZeroSameValue_False() {
        boolean expectedResult = false;
        washingMachineSpecs.setAttributeValue(ATTRIBUTE_CAPACITY, 0);

        boolean result = washingMachineSpecs.setAttributeValue(ATTRIBUTE_CAPACITY, 0);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAsProgrammable() {
        //Arrange
        Programmable expectedResult = (Programmable) washingMachineSpecs;

        //Act
        Programmable result = washingMachineSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAsProgrammable_device() {
        Programmable expectedResult = (Programmable) washingMachineSpecs;
        Programmable result = washingMachineSpecs.asProgrammable();
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetProgramList() {
        //Arrange
        String programName = "fast";
        Programmable programmable = washingMachineSpecs.asProgrammable();
        TimeConstantProgramSpecs programCSpecs = new TimeConstantProgramSpecs();
        programCSpecs.setDuration(30.0);
        programCSpecs.setEnergyConsumption(50.0);
        Program programC = programmable.createNewProgram(programName);
        ((WashingMachineSpecs) washingMachineSpecs).addProgram(programC);

        List<Program> expectedResult = new ArrayList<>();
        expectedResult.add(programC);

        //Act
        List<Program> result = ((WashingMachineSpecs) washingMachineSpecs).getProgramList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        //Act
        boolean result = ((WashingMachineSpecs) washingMachineSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.washingMachineSpecs.asProgrammable();
        TimeConstantProgramSpecs programSpecs = new TimeConstantProgramSpecs();
        programSpecs.setDuration(50.0);
        programSpecs.setEnergyConsumption(20.0);
        Program program = programmable.createNewProgram(programName);
        programmable.addProgram(program);


        boolean expectedResult = false;

        //Act
        boolean result = ((WashingMachineSpecs) washingMachineSpecs).addProgram(program);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        Programmable programmable = this.washingMachineSpecs.asProgrammable();
        TimeConstantProgramSpecs programBSpecs = new TimeConstantProgramSpecs();
        programBSpecs.setDuration(30.0);
        programBSpecs.setEnergyConsumption(50.0);
        Program programB = programmable.createNewProgram(programName);

        boolean expectedResult = true;

        //Act
        boolean result = ((WashingMachineSpecs) washingMachineSpecs).addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

}