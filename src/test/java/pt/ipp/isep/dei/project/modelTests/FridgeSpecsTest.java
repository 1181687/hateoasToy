package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FridgeSpecsTest {
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";
    private Room kitchen;
    private Device fridge;
    private House house;

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        Address address = new Address(null, null, null);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", "room", 1, dim);
        fridge = house.createDevice("Fridge", "fridge Ariston", kitchen);
        this.house.addRoom(kitchen);

        // FridgeSpecs Set
        fridge.setAttributesDevType("Nominal Power", 100.0);
        fridge.setAttributesDevType("Freezer Capacity", 20.0);
        fridge.setAttributesDevType("Refrigerator Capacity", 100.0);
        fridge.setAttributesDevType("Annual Energy Consumption", 10000.0);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fridge";

        //Act
        String result = fridge.getSpecs().getTypeName();

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getEnergyConsumptionInADayTestWithValidValues() {
        // Arrange
        double expectedResult = 27.3972;

        // Act
        double result = fridge.getSpecs().getEnergyConsumptionInADay();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getNominalPower() {
        // Arrange
        double expectedResult = 100.0;

        //Act
        double result = fridge.getSpecs().getNominalPower();

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }

    @Test
    public void getAttributesToString() {

        // Arrange
        // FridgeSpecs Instantiation
        String expectedResult =
                "1 - Freezer Capacity: 20.0\n" +
                        "2 - Refrigerator Capacity: 100.0\n" +
                        "3 - Annual Energy Consumption: 10000.0\n" +
                        "4 - Nominal Power: 100.0\n";

        // Act
        String result = fridge.getSpecs().getAttributesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfAttributes() {
        // Arrange
        int expectedResult = 4;

        // Act
        int result = fridge.getSpecs().getNumberOfAttributes();

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSpecsInAListOfStrings() {
        // Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Freezer Capacity");
        expectedResult.add("Refrigerator Capacity");
        expectedResult.add("Annual Energy Consumption");
        expectedResult.add("Nominal Power");

        // Act
        List<String> result = fridge.getSpecs().getSpecsList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPower() {
        // Arrange
        Object expectedResult = 100.0;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNominalPowerNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("\0Nominal Power");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueFreezerCapacity() {
        // Arrange
        Object expectedResult = 20.0;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("Freezer Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueFreezerCapacityNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("\0Freezer Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueRefrigeratorCapacity() {
        // Arrange
        Object expectedResult = 100.0;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("Refrigerator Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueRefrigeratorCapacityNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("\0Refrigerator Capacity");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumption() {
        // Arrange
        Object expectedResult = 10000.0;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("Annual Energy Consumption");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueAnnualEnergyConsumptionNullChar() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("\0Annual Energy Consumption");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAttributeValueNotAValidSpec() {
        // Arrange
        Object expectedResult = NOT_VALID_ATTRIBUTE;

        // Act
        Object result = fridge.getSpecs().getAttributeValue("Not Valid");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueValidType() {
        // Arrange
        int capacity = 15;

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Freezer Capacity", capacity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Freezer Capacity", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacityValueNullChar() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("\0Freezer Capacity", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueValidType() {
        // Arrange
        double refrigeratorCapacity = 20;

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Refrigerator Capacity", refrigeratorCapacity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Refrigerator Capacity", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacityValueNullChar() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("\0Refrigerator Capacity", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionValueValidType() {
        // Arrange
        double annualPowerConsumption = 20.3;

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Annual Energy Consumption", annualPowerConsumption);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValueNotAValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Annual Energy Consumption", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualPowerConsumptionValueNullChar() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("\0Annual Energy Consumption", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueValueValidType() {
        // Arrange
        double nominalPower = 1.5;

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Nominal Power", nominalPower);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueValueNotValidType() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Nominal Power", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerValueNullChar() {
        // Arrange
        String stuff = "stuff";

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("\0Nominal Power", stuff);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValue() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Nominal Power", 1.5);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacitySameValue() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Freezer Capacity", 20);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeFreezerCapacitySameValueZero() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Freezer Capacity", 0);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Freezer Capacity", 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacitySameValueZero() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Freezer Capacity", 0);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Refrigerator Capacity", 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeNominalPowerSameValueZero() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Freezer Capacity", 0);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Nominal Power", 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionSameValueZero() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Freezer Capacity", 0);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Annual Energy Consumption", 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeRefrigeratorCapacitySameValue() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Refrigerator Capacity", 50);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Refrigerator Capacity", 50);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSetAttributeAnnualEnergyConsumptionSameValue() {
        // Arrange
        fridge.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Act
        boolean result = fridge.getSpecs().setAttributeValue("Annual Energy Consumption", 100);

        // Assert
        assertFalse(result);
    }

    /**
     * Test the method that gives the programmable property to the device.
     * The Kettle is not programmable, so it just has the false return.
     */
    @Test
    public void testIsProgrammable_False() {
        //Arrange
        boolean expectedResult = false;
        //Act
        boolean result = fridge.getSpecs().isProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that returns a Programmable if the device is Programmable.
     * The Kettler is not programmable so the method only return "null" value.
     */
    @Test
    public void testAsProgrammable_null() {
        //Arrange
        Programmable expectedResult = null;

        //Act
        Programmable result = fridge.getSpecs().asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }
}
