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

public class KettleSpecsTest {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_MAXIMUM_VOLUME_WATER = "Maximum volume of Water";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";
    private static final String COLD_WATER_TEMPERATURE = "Cold-Water Temperature";
    private static final String VOLUME_OF_WATER_TO_HEAT = "Volume Of Water To Heat";


    private Room kitchen;
    private Device kettle;
    private House house;
    private DeviceSpecs kettleSpecs;

    /**
     * This method pretends to initialize some attributes of this test class to simplifying all tests.
     */
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
        this.kettle = this.house.createDevice("Kettle", "KettleKitchen", kitchen);
        this.kettle.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 30);
        this.kettle.setAttributesDevType(ATTRIBUTE_MAXIMUM_VOLUME_WATER, 2);
        this.kettle.setAttributesDevType(ATTRIBUTE_PERFORMANCE_RATIO, 0.9);
        this.kettleSpecs = kettle.getSpecs();
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
        boolean result = kettleSpecs.isProgrammable();

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
        Programmable result = kettleSpecs.asProgrammable();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method really return the name of the Device Type.
     */
    @Test
    public void testGetTypeName_typeName() {
        //Arrange
        String expectedResult = "Kettle";

        //Act
        String result = kettleSpecs.getTypeName();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with the already existent value.
     */
    @Test
    public void testSetNominalPower_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double nominalPower = 30;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

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
        double nominalPower = 30;

        //Act
        boolean result = kettleSpecs.setAttributeValue("\0"+ATTRIBUTE_NOMINAL_POWER, nominalPower);

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
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

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
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setNominalPower method with the method setAttributeValue.
     * This test checks if we can set the Nominal Power value with another valid value, for instance 20.
     */
    @Test
    public void testSetNominalPower_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double nominalPower = 20;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_NOMINAL_POWER, nominalPower);

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
        double maxVolWater = 2;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolWater);

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
        double maxVolWater = 2;

        //Act
        boolean result = kettleSpecs.setAttributeValue("\0"+ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolWater);

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
        String maxVolWater = "2";

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolWater);

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
        double maxVolWater = 0;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolWater);

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
        double maxVolWater = 1;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_MAXIMUM_VOLUME_WATER, maxVolWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setPerformanceRatio method with the method setAttributeValue.
     * This test checks if we can set the Performance Ratio value with the already existent value.
     */
    @Test
    public void testSetPerformanceRatio_SameDoubleValue_False() {
        //Arrange
        boolean expectedResult = false;
        double perfRatio = 0.9;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setPerformanceRatio method with the method setAttributeValue.
     * This test checks if we can set the Performance Ratio value with null character.
     */
    @Test
    public void testSetPerformanceRatio_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double perfRatio = 0.9;

        //Act
        boolean result = kettleSpecs.setAttributeValue("\0"+ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setPerformanceRatio method with the method setAttributeValue.
     * This test checks if we can set the Performance Ratio value with an invalid Data Type.
     */
    @Test
    public void testSetPerformanceRatio_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String perfRatio = "0.9";

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setPerformanceRatio method with the method setAttributeValue.
     * This test checks if we can set the Performance Ratio value with zero value.
     */
    @Test
    public void testSetPerformanceRatio_ZeroValue_False() {
        //Arrange
        boolean expectedResult = false;
        double perfRatio = 0;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setPerformanceRatio method with the method setAttributeValue.
     * This test checks if we can set the Performance Ratio value with another valid value, for instance 1.
     */
    @Test
    public void testSetPerformanceRatio_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double perfRatio = 1;

        //Act
        boolean result = kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setVolumeOfWaterToHeat method with the method setAttributeValue.
     * This test checks if we can set the Volume of Water to Heat value with another valid value, for instance 1.
     */
    @Test
    public void testSetVolumeOfWaterToHeat_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double volWater = 1;

        //Act
        boolean result = kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setVolumeOfWaterToHeat method with the method setAttributeValue.
     * This test checks if we can set the Volume of Water to Heat value with another negative value.
     */
    @Test
    public void testSetVolumeOfWaterToHeat_NegativeValue_False() {
        //Arrange
        boolean expectedResult = false;
        double volWater = -1;

        //Act
        boolean result = kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setVolumeOfWaterToHeat method with the method setAttributeValue.
     * This test checks if we can set the Volume of Water to Heat value  with null character.
     */
    @Test
    public void testSetVolumeOfWaterToHeat_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        double volWater = -1;

        //Act
        boolean result = kettleSpecs.setAttributeValue("\0"+VOLUME_OF_WATER_TO_HEAT, volWater);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setVolumeOfWaterToHeat method with the method setAttributeValue.
     * This test checks if we can set the Volume of Water to Heat value with an invalid Data Type.
     */
    @Test
    public void testSetVolumeOfWaterToHeat_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String volWater = "-1";

        //Act
        boolean result = kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);

        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the setVolumeOfWaterToHeat method with the method setAttributeValue.
     * This test checks if we can set the Volume of Water to Heat value with a value larger than the maximum volume of water.
     */
    @Test
    public void testSetVolumeOfWaterToHeat_ValueGreaterThanMaxVolume_False() {
        //Arrange
        boolean expectedResult = false;
        double volWater = 3;

        //Act
        boolean result = kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setColdWaterTemperature method with the method setAttributeValue.
     * This test checks if we can set the Cold Water Temperature value with a value larger than the boiling temperature of water (100ºC).
     */
    @Test
    public void testSetColdWaterTemperature_ValueGreaterThanBoilingTemp_False() {
        //Arrange
        boolean expectedResult = false;
        double coldWaterTemp = 101;

        //Act
        boolean result = kettleSpecs.setAttributeValue(COLD_WATER_TEMPERATURE, coldWaterTemp);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setColdWaterTemperature method with the method setAttributeValue.
     * This test checks if we can set the Cold Water Temperature value with an invalid Data Type.
     */
    @Test
    public void testSetColdWaterTemperature_InvalidDataType_False() {
        //Arrange
        boolean expectedResult = false;
        String coldWaterTemp = "101";

        //Act
        boolean result = kettleSpecs.setAttributeValue(COLD_WATER_TEMPERATURE, coldWaterTemp);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setColdWaterTemperature method with the method setAttributeValue.
     * This test checks if we can set the Cold Water Temperature value with null character.
     */
    @Test
    public void testSetColdWaterTemperature_NullCharacter_False() {
        //Arrange
        boolean expectedResult = false;
        String  coldWaterTemp = "101";

        //Act
        boolean result = kettleSpecs.setAttributeValue("\0"+COLD_WATER_TEMPERATURE, coldWaterTemp);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the setColdWaterTemperature method with the method setAttributeValue.
     * This test checks if we can set the Cold Water Temperature value with a valid value.
     */
    @Test
    public void testSetColdWaterTemperature_ValidValue_True() {
        //Arrange
        boolean expectedResult = true;
        double coldWaterTemp = 99;

        //Act
        boolean result = kettleSpecs.setAttributeValue(COLD_WATER_TEMPERATURE, coldWaterTemp);

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
        boolean result = kettleSpecs.setAttributeValue("Not valid name", nominalPower);

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
        boolean result = kettleSpecs.setAttributeValue("", nominalPower);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the getNominalPower method really returns the Nominal Power attribute value.
     */
    @Test
    public void testGetNominalPower_NominalPowerValue() {
        //Arrange
        double expectedResult = 30;
        //Act
        double result = kettleSpecs.getNominalPower();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the getNominalPower method returns the correct value of Energy Consumption formula.
     */
    @Test
    public void testGetEnergyConsumptionInADay_EnergyConsumptionValue() {
        //Arrange
        double coldWaterTemp = 99;
        boolean coldWaterTempValue = kettleSpecs.setAttributeValue(COLD_WATER_TEMPERATURE, coldWaterTemp);
        double volWater = 1;
        boolean volWaterValue = kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);
        double perfRatio = 1;
        boolean perfRatioValue = kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);


        double expectedResult = 1.163 / 1000 * 1 * (100 - 99) * 1;
        //Act
        double result = kettleSpecs.getEnergyConsumptionInADay();
        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetEnergyConsumptionInADay_EnergyConsumptionValue2() {
        //Arrange
        double coldWaterTemp = 95;
        kettleSpecs.setAttributeValue(COLD_WATER_TEMPERATURE, coldWaterTemp);
        double volWater = 1.9;
        kettleSpecs.setAttributeValue(VOLUME_OF_WATER_TO_HEAT, volWater);
        double perfRatio = 3;
        kettleSpecs.setAttributeValue(ATTRIBUTE_PERFORMANCE_RATIO, perfRatio);


        double expectedResult = 1.163 / 1000 * 1.9 * (100 - 95) * 3;
        //Act
        double result = kettleSpecs.getEnergyConsumptionInADay();
        //Assert
        assertEquals(expectedResult, result, 0.0000001);
    }

    /**
     * Test if the method getAttributesToString returns the String of the Attributes with the respective value.
     */
    @Test
    public void testGetAttributesToString_ShowAttributesWithValue() {
        //Arrange
        String expectedResult =
                "1 - Maximum Volume of Water: " + 2.0 + "\n" +
                        "2 - Performance Ratio: " + 0.9 + "\n" +
                        "3 - Nominal Power: " + 30.0 + "\n";
        //Act
        String result = kettleSpecs.getAttributesToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that shows the number of Attributes of this Device, in this case, there are 3.
     */
    @Test
    public void testGetNumberOfAttributes_NumberOfAttributes() {
        //Arrange
        int expectedResult = 3;

        //Act
        int result = kettleSpecs.getNumberOfAttributes();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the method that gets a String List of the attibutes of this device.
     */
    @Test
    public void testGetSpecsList_ListOfStringsOfAttibutes() {
        //Arrange
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(ATTRIBUTE_MAXIMUM_VOLUME_WATER);
        expectedResult.add(ATTRIBUTE_PERFORMANCE_RATIO);
        expectedResult.add(ATTRIBUTE_NOMINAL_POWER);

        //Act
        List<String> result = kettleSpecs.getSpecsList();
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
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("Atributo Qualquer");
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterMaxVolWater (){
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("\0"+ATTRIBUTE_MAXIMUM_VOLUME_WATER);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterPerfRatio (){
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("\0"+ATTRIBUTE_PERFORMANCE_RATIO);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterColdWaterTemp (){
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("\0"+COLD_WATER_TEMPERATURE);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullCharacterNomPower (){
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("\0"+ATTRIBUTE_NOMINAL_POWER);
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test the return "not a valid attribute" of the method getAttributeValue,
     * when inserted an null character attribute.
     */
    @Test
    public void testGetAttributeValue_NullVolWaterHeat (){
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("\0"+VOLUME_OF_WATER_TO_HEAT);
        //Assert
        assertEquals(expectedResult, result);
    }


    /**
     * Test the return "not a valid attribute" of the method getAttributeValue.
     */
    @Test
    public void testGetAttributeValue_NullAttribute() {
        //Arrange
        String expectedResult = "not a valid attribute";
        //Act
        Object result = kettleSpecs.getAttributeValue("");
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
        String result = kettleSpecs.getAttributeDataType(ATTRIBUTE_NOMINAL_POWER);

        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the Maximum Volume of Water.
     */
    @Test
    public void testGetAttributeDataType_MaxVolumeOfWater() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = kettleSpecs.getAttributeDataType(ATTRIBUTE_MAXIMUM_VOLUME_WATER);

        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the Performance Ratio.
     */
    @Test
    public void testGetAttributeDataType_PerformanceRatio() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = kettleSpecs.getAttributeDataType(ATTRIBUTE_PERFORMANCE_RATIO);

        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the Cold Water Temperature.
     */
    @Test
    public void testGetAttributeDataType_ColdWaterTemperature() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = kettleSpecs.getAttributeDataType(COLD_WATER_TEMPERATURE);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test if the method getAttributeDataType shows the Data type of a valid attribute,
     * in this case, the volume Of Water To Heat.
     */
    @Test
    public void testGetAttributeDataType_volumeOfWaterToHeat() {
        //Arrange
        String expectedResult = "Double";

        //Act
        String result = kettleSpecs.getAttributeDataType(VOLUME_OF_WATER_TO_HEAT);

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
        String result = kettleSpecs.getAttributeDataType("Atributo qualquer");

        //Assert
        assertEquals(expectedResult, result);

    }

}
