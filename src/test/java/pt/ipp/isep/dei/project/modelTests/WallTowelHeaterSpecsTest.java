package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.Programmable;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTowelHeaterSpecsTest {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_TIME = "Time";

    private Room kitchen;
    private Device wallTowelHeater;
    private DeviceSpecs specs;
    private House house;
    private static final String NOT_VALID_ATTRIBUTE = "not a valid attribute";

    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Dimension dim = new Dimension(3, 5, 6);
        kitchen = new Room("Kitchen", 1, dim);
        wallTowelHeater = house.createDevice("Freezer", "Freezer Ariston", kitchen);
        this.house.addRoom(kitchen);

        // FridgeSpecs Set
        wallTowelHeater.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 100);
        wallTowelHeater.setAttributesDevType(ATTRIBUTE_TIME, 10);
        this.specs = wallTowelHeater.getSpecs();
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
        boolean result = specs.isProgrammable();

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
        Programmable result = specs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method really return the name of the Device Type.
     */
    @Test
    public void testGetTypeName_typeName() {
        //Arrange
        String expectedResult = "WallTowelHeater";

        //Act
        String result = specs.getTypeName();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with the already existent
     * value.
     */
    @Test
    public void testSetNominalPower_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 100;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with null character .
     */
    @Test
    public void testSetNominalPower_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 100;

        //Act
        boolean result = specs.setAttributeValue("\0"+ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with an invalid Data Type.
     */
    @Test
    public void testSetNominalPower_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String nominalPower = "30";

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with zero value.
     */
    @Test
    public void testSetNominalPower_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with another valid value,
     * for instance 100.
     */
    @Test
    public void testSetNominalPower_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double nominalPower = 100;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setMaximumVolumeOfWater method with the method setAttributeValue.
     * This test checks if we can set the Maximum Volume of Water value with the already existent value.
     */
    @Test
    public void testSetMaximumVolumeOfWater_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double time = 10;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, time);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setMaximumVolumeOfWater method with the method setAttributeValue.
     * This test checks if we can set the Maximum Volume of Water value with null character.
     */
    @Test
    public void testSetMaximumVolumeOfWater_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double time = 10;

        //Act
        boolean result = specs.setAttributeValue("\0" + ATTRIBUTE_TIME, time);

        //Assert
        assertEquals(expectedResult, result);
    }



    /**
     * Test the setMaximumVolumeOfWater method with the method setAttributeValue.
     * This test checks if we can set the Maximum Volume of Water value with with an invalid Data Type.
     */
    @Test
    public void testSetMaximumVolumeOfWater_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String time = "2";

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, time);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setMaximumVolumeOfWater method with the method setAttributeValue.
     * This test checks if we can set the Maximum Volume of Water value with zero value.
     */
    @Test
    public void testSetMaximumVolumeOfWater_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double time = 0;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, time);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setMaximumVolumeOfWater method with the method setAttributeValue.
     * This test checks if we can set the Maximum Volume of Water value with another valid value, for instance 1.
     */
    @Test
    public void testSetMaximumVolumeOfWater_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double time = 1;

        //Act
        boolean result = specs.setAttributeValue(ATTRIBUTE_TIME, time);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setAttributeValue method in the case that there isn't a valid attribute name.
     */
    @Test
    public void testSetAttributeValue_InvalidAttributeName_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue("Not valid name", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setAttributeValue method in the case that there isn't a valid attribute name.
     */
    @Test
    public void testSetAttributeValue_NullAttributeName_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 0;

        //Act
        boolean result = specs.setAttributeValue("", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the getNominalPower method really returns the Nominal Power attribute value.
     */
    @Test
    public void testGetNominalPower_NominalPowerValue() {
        //Arrange
        double expectedResult = 100;
        //Act
        double result = specs.getNominalPower();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the getNominalPower method returns the correct value of Energy Consumption
     * formula.
     */
    @Test
    public void testGetEnergyConsumptionInADay_EnergyConsumptionValue() {
        //Arrange
        double time = 2;
        boolean coldWaterTempValue = specs.setAttributeValue(ATTRIBUTE_TIME, time);

        double expectedResult = 200.0;
        //Act
        double result = specs.getEnergyConsumptionInADay();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method getAttributesToString returns the String of the Attributes
     * with the respective value.
     */
    @Test
    public void testGetAttributesToString_ShowAttributesWithValue() {
        //Arrange
        String expectedResult =
                "1 - Nominal Power: " + 100 + "\n";
        //Act
        String result = specs.getAttributesToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that shows the number of Attributes of this Device, in this case,
     * there is 1.
     */
    @Test
    public void testGetNumberOfAttributes_NumberOfAttributes() {
        //Arrange
        int expectedResult = 1;

        //Act
        int result = specs.getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that gets a String List of the attributes of this device.
     */
    @Test
    public void testGetSpecsList_ListOfStringsOfAttibutes() {
        //Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(ATTRIBUTE_NOMINAL_POWER);

        //Act
        List<String> result = specs.getSpecsList();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an invalid attribute.
     */
    @Test
    public void testGetAttributeValue_NotAValidAttribute() {
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = specs.getAttributeValue("Atributo Qualquer");
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted a null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterMaxVolWater (){
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = specs.getAttributeValue("\0"+ATTRIBUTE_TIME);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted a null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterNomPower (){
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = specs.getAttributeValue("\0"+ATTRIBUTE_NOMINAL_POWER);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue.
     */
    @Test
    public void testGetAttributeValue_NullAttribute() {
        //Arrange
        String expectedResult = NOT_VALID_ATTRIBUTE;
        //Act
        Object result = specs.getAttributeValue("");
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the nominal power.
     */
    @Test
    public void testGetAttributeDataType_NominalPower() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = specs.getAttributeDataType(ATTRIBUTE_NOMINAL_POWER);

        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the Time.
     */
    @Test
    public void testGetAttributeDataType_MaxVolumeOfWater() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = specs.getAttributeDataType(ATTRIBUTE_TIME);

        //Assert
        assertEquals(expectedResult, result);

    }


    /**
     * Test if the method getAttributeDataType shows the String "not a valid attribute",
     * in case of do not insert a valid name of attribute.
     */
    @Test
    public void testGetAttributeDataType_InvalidAttribute() {
        //Arrange
        String expectedResult = "not a valid attribute";

        //Act
        String result = specs.getAttributeDataType("Atributo qualquer");

        //Assert
        assertEquals(expectedResult, result);

    }

}
