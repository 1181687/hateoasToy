package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineSpecsTest {
    private Room room;
    private Device washingMachine;
    private final String CAPACITY = "Capacity";
    private final String NOMINAL_POWER = "Nominal Power";

    @BeforeEach
    public void StartUp() {

        Dimension dimension = new Dimension(2, 2, 2);
        this.room = new Room("Kitchen", 0, dimension);

        WashingMachineType type = new WashingMachineType();
        this.washingMachine = type.createDevice("Wm1", room);

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

        String expectedResult = "1 - Capacity: 20.0\n" +
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

        Object expectedResult = 30.0;
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
}