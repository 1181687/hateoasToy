package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimension;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.devices.TimeConstantProgramSpecs;
import pt.ipp.isep.dei.project.model.devices.dishwasher.DishWasherSpecs;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DishWasherSpecsTest {
    private Room kitchen;
    private Device dishWasher;
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
        this.kitchen = new Room("Kitchen", 1, dim);
        this.house.addRoom(kitchen);
        this.dishWasher = this.house.createDevice("Dishwasher", "dishwasher Bosch", kitchen);
        this.dishWasher.setAttributesDevType("Nominal Power", 30);
        this.dishWasher.setAttributesDevType("Capacity", 30);

    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Dishwasher";

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
    public void testEmptyConstructor() {
        //Arrange
        dishWasher.setAttributesDevType("Nominal Power", 30);

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
    public void testGetAttributeValueCapacity() {
        // Arrange
        Object expectedResult = 30;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueDuration() {
        // Arrange
        dishWasher.setAttributesDevType("Duration", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = dishWasher.getSpecs().getAttributeValue("Duration");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = -1;
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
    public void testSetAttributeDurationValueNotAValidType() {
        // Arrange
        String attribute = "stuff";
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Duration", attribute);
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
    public void testSetAttributeDurationValidValue() {
        // Arrange
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Duration", 100.0);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeDurationSameValue() {
        // Arrange
        dishWasher.setAttributesDevType("Duration", 100.0);
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Duration", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValueZero() {
        // Act
        boolean result = dishWasher.getSpecs().setAttributeValue("Duration", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = dishWasher.getSpecs().getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        boolean expectedResult = false;
        DeviceSpecs dishSpecs = dishWasher.getSpecs();
        //Act
        boolean result = ((DishWasherSpecs) dishSpecs).addProgram(null);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.dishWasher.asProgrammable();
        TimeConstantProgramSpecs programA = programmable.newProgram(programName, duration, energyConsumption);
        TimeConstantProgramSpecs programB = programmable.newProgram(programName, duration, energyConsumption);
        programmable.addProgram(programA);
        DeviceSpecs dishSpecs = dishWasher.getSpecs();

        boolean expectedResult = false;

        //Act
        boolean result = ((DishWasherSpecs) dishSpecs).addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.dishWasher.asProgrammable();
        TimeConstantProgramSpecs programA = programmable.newProgram(programName, duration, energyConsumption);
        DeviceSpecs dishSpecs = dishWasher.getSpecs();

        boolean expectedResult = true;

        //Act
        boolean result = ((DishWasherSpecs) dishSpecs).addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }
}