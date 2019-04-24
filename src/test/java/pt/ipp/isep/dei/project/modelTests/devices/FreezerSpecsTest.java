package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.devices.freezer.FreezerType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FreezerSpecsTest {
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";
    private Room kitchen;
    private Device freezer;

    @BeforeEach
    public void StartUp() {

        //Room
        Dimension dim = new Dimension(3, 5, 6);
        RoomId roomId = new RoomId("kitchen");
        this.kitchen = new Room(roomId, "room", 1, dim);
        FreezerType freezerType = new FreezerType();
        this.freezer = freezerType.createDevice("Freezer Ariston");
        kitchen.addDevice(freezer);
        this.freezer.setAttributesDevType("Nominal Power", 100);
        this.freezer.setAttributesDevType("Freezer Capacity", 30);
        this.freezer.setAttributesDevType("Annual Energy Consumption", 10000.0);
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
                "1 - Freezer Capacity: 30.0\n" +
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
        Object expectedResult = 30.0;

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
        Object expectedResult = NOT_VALID_ATTRIBUTE;

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
    public void setAttributeNominalPowerValueTest_NotAValidType() {
        // Arrange
        double nominalPower = 1.5;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setAttributeNominalPowerTest_SameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeFreezerCapacityTest_SameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Assert
        assertFalse(result);
    }

    @Test
    public void setAttributeAnnualEnergyConsumptionTest_SameValue() {
        // Arrange
        freezer.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfFreezerIsProgrammable() {
        //Act
        boolean result = freezer.getSpecs().isProgrammable();
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGetFreezerAsProgrammable_ReturnsNull() {
        //Arrange
        Programmable expectedResult = null;
        //Act
        Programmable result = freezer.getSpecs().asProgrammable();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValue_NullChar() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("\0Annual Energy Consumption", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValue_NullChar() {
        // Arrange
        int capacity = 15;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("\0Freezer Capacity", capacity);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValue_NullChar() {
        // Arrange
        int nominalPower = 15;

        // Act
        boolean result = freezer.getSpecs().setAttributeValue("\0Nominal Power", nominalPower);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAttributeValueNominalPower_NullChar() {
        // Arrange
        Object expectedResult = "not a valid attribute";

        // Act
        Object result = freezer.getSpecs().getAttributeValue("\0Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueFreezerCapacity_NullChar() {
        // Arrange
        Object expectedResult = "not a valid attribute";

        // Act
        Object result = freezer.getSpecs().getAttributeValue("\0Freezer Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption_NullChar() {
        // Arrange
        Object expectedResult = "not a valid attribute";

        // Act
        Object result = freezer.getSpecs().getAttributeValue("\0Annual Energy Consumption");

        // Assert
        assertEquals(expectedResult, result);
    }
}