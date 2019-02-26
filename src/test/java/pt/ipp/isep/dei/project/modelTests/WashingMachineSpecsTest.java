package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

class WashingMachineSpecsTest {
    private Room room;
    private Device washingMachine;
    private final String CAPACITY = "Capacity";
    private final String NOMINAL_POWER = "Nominal Power";
    private Reading reading0;
    private Reading reading1;
    private Reading reading2;

    @BeforeEach
    public void StartUp() {

        Dimension dimension = new Dimension(2, 2, 2);
        this.room = new Room("Kitchen", 0, dimension);

        WashingMachineType type = new WashingMachineType();
        this.washingMachine = type.createDevice("Wm1", room);

        // Reading
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        reading0 = new Reading(3, time0);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        reading1 = new Reading(5, time1);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);
        reading2 = new Reading(7, time2);
        washingMachine.addReadingsToTheList(reading0);
        washingMachine.addReadingsToTheList(reading1);
        washingMachine.addReadingsToTheList(reading2);

    }

    @Test
    public void testGetReadings() {
        //Arrange
        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(reading0);
        expectedResult.add(reading1);
        expectedResult.add(reading2);

        //Act
        List<Reading> result = washingMachine.getReadings();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetTypeName() {
        //Arrange
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs();

        String expectedResult = "Washing Machine";

        //Act
        String result = washingMachineSpecs.getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetNominalPower() {
        //Arrange
        double nominalPower = 30;

        this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);

        double expectedResult = 30;

        //Act
        double result = this.washingMachine.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange
        double nominalPower = 30;
        this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);

        double expectedResult = 30;

        //Act
        double result = this.washingMachine.getNominalPower();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityFalse() {
        //Arrange
        int capacity = 20;

        boolean expectedResult = false;
        this.washingMachine.setAttributesDevType(CAPACITY, capacity);
        //Act
        boolean result = this.washingMachine.setAttributesDevType(CAPACITY, capacity);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetCapacityTrue() {
        //Arrange
        int capacity1 = 20;
        int capacity2 = 30;
        this.washingMachine.setAttributesDevType(CAPACITY, capacity1);

        boolean expectedResult = true;

        //Act
        boolean result = this.washingMachine.setAttributesDevType(CAPACITY, capacity2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerFalse() {
        //Arrange

        double nominalPower = 30;
        this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);
        boolean expectedResult = false;

        //Act
        boolean result = this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNominalPowerTrue() {
        //Arrange
        double nominalPower = 30.0;
        double nominalPower2 = 23.0;
        this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);

        boolean expectedResult = true;

        //Act
        boolean result = this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower2);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributesToString() {
        //Arrange
        double capacity = 20;
        double nominalPower = 30;

        this.washingMachine.setAttributesDevType(CAPACITY, capacity);
        this.washingMachine.setAttributesDevType(NOMINAL_POWER, nominalPower);

        String expectedResult = "1 - Capacity: 20\n" +
                "2 - Nominal Power: 30.0\n";

        //Act
        String result = this.washingMachine.getDevSpecsAttributesToString();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityTrue() {
        //Arrange
        int capacity = 31;
        double nominalPower = 30;
        this.washingMachine.setAttributesDevType(CAPACITY,capacity);
        this.washingMachine.setAttributesDevType(NOMINAL_POWER,nominalPower);
        double value = 20.6;

        boolean expectedResult = true;
        //Act
        boolean result = this.washingMachine.setAttributesDevType("Capacity", value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetAttributeNonExistentFalse() {
        //Arrange
        int capacity = 31;

        int attribute = 3;
        double value = 20.6;
        this.washingMachine.setAttributesDevType(CAPACITY,capacity);

        boolean expectedResult = false;

        //Act
        boolean result = this.washingMachine.setAttributesDevType("Non-existant attribute", value);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeNominalPowerTrue() {
        //Arrange
        double nominalPower = 30;
        this.washingMachine.setAttributesDevType(NOMINAL_POWER,nominalPower);
        double value = 20.6;

        boolean expectedResult = true;

        //Act
        boolean result = this.washingMachine.setAttributesDevType("Nominal Power", value);
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetNumberOfAttributes() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;

        this.washingMachine.setAttributesDevType(CAPACITY,capacity);
        this.washingMachine.setAttributesDevType(NOMINAL_POWER,nominalPower);

        int expectedResult = 2;

        //Act
        int result = this.washingMachine.getNumberOfSpecsAttributes();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEnergyConsumptionInADay() {
        //Arrange
        int capacity = 20;
        double nominalPower = 30;
        this.washingMachine.setAttributesDevType(CAPACITY,capacity);
        this.washingMachine.setAttributesDevType(NOMINAL_POWER,nominalPower);

        double expectedResult = 0;
        //Act
        double result = this.washingMachine.getEnergyConsumptionInADay();
        //Assert
        assertEquals(result, expectedResult);

    }
    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Capacity");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = room.getDeviceByPosition(0).getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        // FridgeSpecs Instantiation
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        room.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 100.0;
        // Act
        Object result = room.getDeviceByPosition(0).getAttributeValue("Nominal Power");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueCapacity() {
        // Arrange
        // FridgeSpecs Instantiation
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        room.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 30;
        // Act
        Object result = room.getDeviceByPosition(0).getAttributeValue("Capacity");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueDuration() {
        // Arrange
        // FridgeSpecs Instantiation
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        room.getDeviceByPosition(0).setAttributesDevType("Duration", 30);
        room.getDeviceByPosition(0).setAttributesDevType("Capacity", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = room.getDeviceByPosition(0).getAttributeValue("Duration");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueEnergyConsumption() {
        // Arrange
        // FridgeSpecs Instantiation
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", 30);

        Object expectedResult = 30.0;
        // Act
        Object result = room.getDeviceByPosition(0).getAttributeValue("Energy Consumption");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        // FridgeSpecs Instantiation
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        room.getDeviceByPosition(0).setAttributesDevType("Capacity", 20.0);

        Object expectedResult = -1;
        // Act
        Object result = room.getDeviceByPosition(0).getAttributeValue("Not Valid");
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeCapacityValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Capacity", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Duration", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNotAValidType() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Nominal Power", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionNotValid() {
        // Arrange
        String tuff = "coiso";
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", tuff);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationSameValue() {
        // Arrange
        room.getDeviceByPosition(0).setAttributesDevType("Duration", 100.0);
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Duration", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeDurationValidValue() {
        // Arrange
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Duration", 100.0);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionSameValue() {
        // Arrange
        room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", 100.0);
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", 100.0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionZero() {
        // Arrange
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", 0);
        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeEnergyConsumptionValidValue() {
        // Arrange
        // Act
        boolean result = room.getDeviceByPosition(0).setAttributesDevType("Energy Consumption", 100.0);
        // Assert
        assertTrue(result);
    }

    @Test
    public void getAttributeDataTypeTest() {
        // arrange
        String attributeDataType = "Integer";
        // act
        String result = washingMachine.getAttributeDataType("Integer");
        // assert
        assertEquals(attributeDataType, result);
    }

    @Test
    public void testAddProgram_WithNullProgram_ShouldReturnFalse() {
        //Arrange
        Program program = null;
        boolean expectedResult = false;
        Programmable programmable = this.washingMachine.asProgrammable();
        //Act
        boolean result = programmable.addProgram(program);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramAlreadyInTheList_ShouldReturnFalse() {
        //Arrange
        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.washingMachine.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);
        Program programB = programmable.newProgram(programName, duration, energyConsumption);
        programmable.addProgram(programA);
        boolean expectedResult = false;

        //Act
        boolean result = programmable.addProgram(programB);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddProgram_ProgramIsNotInTheList_ShouldReturnTrue() {
        //Arrange

        String programName = "fast";
        double duration = 15;
        double energyConsumption = 1;
        Programmable programmable = this.washingMachine.asProgrammable();
        Program programA = programmable.newProgram(programName, duration, energyConsumption);

        boolean expectedResult = true;

        //Act
        boolean result = programmable.addProgram(programA);

        //Assert
        assertEquals(expectedResult, result);
    }
}